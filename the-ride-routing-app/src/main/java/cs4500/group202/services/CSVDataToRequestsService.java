package cs4500.group202.services;

import cs4500.group202.model.enums.RequestType;
import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.ILatLong;
import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.interfaces.services.ICSVDataToRequestsService;
import cs4500.group202.interfaces.services.IDistanceTimeEstimateService;
import cs4500.group202.interfaces.services.IGeoCodingService;
import cs4500.group202.model.Address;
import cs4500.group202.model.Request;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service for the purposes of reading CSV data into a collection of requests.
 */
@Service
public class CSVDataToRequestsService implements ICSVDataToRequestsService {


  // Time interval duration in minutes
  private final int requestDefaultDuration = 15;

  // Longest allowable ride duration
  private final int longestRideDuration = 60;

  // Load time for request (in minutes)
  private final int loadTime = 3;

  // GeoCoding service
  @Autowired
  private IGeoCodingService geoCodingService;

  // Time/distance estimator service
  @Autowired
  private IDistanceTimeEstimateService distanceTimeEstimateService;

  CSVDataToRequestsService(
      IGeoCodingService geoCodingService,
      IDistanceTimeEstimateService distanceTimeEstimateService) {
    this.geoCodingService = geoCodingService;
    this.distanceTimeEstimateService = distanceTimeEstimateService;
  }

  @Override
  public List<IRequest> readDataToRequests(Reader reader) throws Exception {
    List<IRequest> result = new ArrayList<>();
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
    for (CSVRecord record : records) {
      result.add(recordToRequest(record));
    }
    return result;
  }

  /**
   * Converts a properly formatted CSV record into a corresponding IRequest object.
   * @param record CSVRecord to be converted
   * @return Corresponding IRequest object
   */
  private IRequest recordToRequest(CSVRecord record)  {
    // Misc. Fields
    int requestId = 0;
    int tripId = Integer.parseInt(record.get("Trip ID")); // Unsure about this
    boolean subscription = record.get("Subscription (Y or N)").equals("Y");
    boolean ada = record.get("ADA/Non").equals("ADA");
    int pcas = Integer.parseInt(record.get("PCAs"));
    int companions = Integer.parseInt(record.get("Companions"));
    boolean serviceAnimal = record.get("ServiceAnimal").equals("Y");

    // Request Type field (pick up or drop off)
    RequestType requestType = (record.get("Anchor").equals("P")) ? RequestType.PICKUP
        : RequestType.DROPOFF;

    // AddressEntity fields
    IAddress pickUpAddress = getPickUpAddressFromRecord(record);
    IAddress dropOffAddress = getDropOffAddressFromRecord(record);

    // Time fields
    String timeString = record.get("RequestTime");
    boolean isPm = timeString.contains("PM");
    int hour = timeString.contains("AM") ?
        Integer.parseInt(timeString.substring(0, timeString.indexOf(':'))) :
        12 + Integer.parseInt(timeString.substring(0, timeString.indexOf(':')));
    int minute = Integer.parseInt(timeString.substring(timeString.indexOf(':') + 1,
        timeString.indexOf(':') + 3));
    // Represents the middle of the relevant time interval
    LocalTime middleTime = new LocalTime(hour, minute);
    LocalTime startPickUpTimeWindow;
    LocalTime endPickUpTimeWindow;
    LocalTime startDropOffTimeWindow;
    LocalTime endDropOffTimeWindow;
    Duration travelTime = distanceTimeEstimateService.getTravelTime(pickUpAddress, dropOffAddress);
    if (requestType == RequestType.DROPOFF)  {
      startDropOffTimeWindow = middleTime;
      endDropOffTimeWindow = middleTime.plusMinutes(requestDefaultDuration);
      startPickUpTimeWindow = startDropOffTimeWindow.minusMinutes(loadTime + longestRideDuration);
      endPickUpTimeWindow = endDropOffTimeWindow.minusMinutes(loadTime
          + (int) travelTime.getStandardMinutes());
      // This should not be more than 60, so casting should be safe
    }
    else  {
      startPickUpTimeWindow = middleTime;
      endPickUpTimeWindow = middleTime.plusMinutes(requestDefaultDuration);
      endDropOffTimeWindow = endPickUpTimeWindow.plusMinutes(loadTime + longestRideDuration);
      startDropOffTimeWindow = startPickUpTimeWindow.plusMinutes(loadTime
          + (int) travelTime.getStandardMinutes());
    }
    return new Request(requestId, tripId, subscription, ada, requestType, startPickUpTimeWindow,
        endPickUpTimeWindow, startDropOffTimeWindow, endDropOffTimeWindow, pickUpAddress,
        dropOffAddress, pcas, companions, serviceAnimal);
  }

  /**
   * Retrieves pick up address info from a record.
   * @param record CSVRecord to be parsed.
   * @return IAddress object for the pick up address
   */
  private IAddress getPickUpAddressFromRecord(CSVRecord record)  {
    int pickUpHouseNumber = Integer.parseInt(record.get("PickHouseNumber"));
    String pickUpStreet = record.get("PickAddress1");
    String pickUpCity = record.get("Pickcity");
    String pickUpZip = "0" + record.get("pickzip");
    String pickUpAddressString = pickUpHouseNumber + " " + pickUpStreet + ", " + pickUpCity
        + " MA " + pickUpZip;
    ILatLong pickUpLatLong = geoCodingService.getLatLongWithGeocode(pickUpAddressString);
    return new Address(0, pickUpHouseNumber, pickUpStreet, pickUpCity,
        pickUpZip, pickUpLatLong);
  }

  /**
   * Retrieves drop off address info from a record.
   * @param record CSVRecord to be parsed.
   * @return IAddress object for the drop off address
   */
  private IAddress getDropOffAddressFromRecord(CSVRecord record)  {
    int dropOffHouseNumber = Integer.parseInt(record.get("DropHouseNumber"));
    String dropOffStreet = record.get("DropAddress1");
    String dropOffCity = record.get("Dropcity");
    String dropOffZip = "0" + record.get("DropZip");
    String dropOffAddressString = dropOffHouseNumber + " " + dropOffStreet + ", " + dropOffCity
        + " MA " + dropOffZip;
    ILatLong dropOffLatLong = geoCodingService.getLatLongWithGeocode(dropOffAddressString);
    return new Address(0, dropOffHouseNumber, dropOffStreet, dropOffCity,
        dropOffZip, dropOffLatLong);
  }
}
