package cs4500.group202.services;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.interfaces.services.ITripManifestToCSVService;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalTime;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * A service to transform tripManifest objects into a CSV output
 */
@Service
public class TripManifestToCSVService implements ITripManifestToCSVService {

  final static Logger logger =
      LogManager.getLogger(TripManifestToCSVService.class.getName());

  private static final char CSVSEPARATOR = ',';

  @Override
  public List<String> writeManifestToCSV(List<IManifestEntry> tripManifest, String location)
      throws IOException {
    List<String> result = new ArrayList<>();
    HashMap<Integer, List<IManifestEntry>> entriesByVehicle = new HashMap<>();

    for (IManifestEntry entry : tripManifest)  {
      int vehicleId = entry.getVehicleId();
      if (entriesByVehicle.get(vehicleId) == null)  {
        entriesByVehicle.put(entry.getVehicleId(), new ArrayList<>());
      }
      entriesByVehicle.get(vehicleId).add(entry);
    }


    for (int id : entriesByVehicle.keySet()) {
      System.out.println("Hash key ID: " + id);
      String currLocation = location + "-vehicle-" + id + ".csv";
      try (FileWriter writer = new FileWriter(currLocation)) {
        writeLine(writer, Arrays.asList("Ride ID", "Trip ID", "PickHouseNumber",
            "PickAddress", "PickCity", "PickZip",
            "DropHouseNumber", "DropAddress", "DropCity", "DropZip",
            "PickTime", "DropTime"));
        for (IManifestEntry tripEntry : entriesByVehicle.get(id)) {
          IAddress pAddress = tripEntry.getPickupAddress();
          IAddress dAddress = tripEntry.getDropOffAddress();
          writeLine(writer, Arrays.asList(
              String.valueOf(tripEntry.getRideID()), String.valueOf(tripEntry.getTripId()),
              String.valueOf(pAddress.getStreetNumber()), pAddress.getStreetName(),
              pAddress.getCity(), pAddress.getZipCode(),
              String.valueOf(dAddress.getStreetNumber()), dAddress.getStreetName(),
              dAddress.getCity(), dAddress.getZipCode(),
              tripEntry.getPickupTime().toString(), tripEntry.getDropOffTime().toString()));
        }
        result.add(currLocation.substring(8, currLocation.length()));
        try {
          writer.flush();

        } finally {
          writer.close();
        }

      } catch (Exception e) {
        e.printStackTrace();
        logger.error("Error in CsvFileWriter", e);
      }
    }
    return result;
  }


  /**
   * To format strings into acceptable CSV format
   * from: https://www.mkyong.com/java/how-to-export-data-to-csv-file-java/
   *
   * @param value the string to formatted
   * @return the formatted string
   */
  private static String followCVSformat(String value) {

    String result = value;
    if (result.contains("\"")) {
      result = result.replace("\"", "\"\"");
    }
    return result;

  }

  /**
   * To create a row in a csv file from a list of strings
   * from: https://www.mkyong.com/java/how-to-export-data-to-csv-file-java/
   */
  private static void writeLine(Writer w, List<String> values) throws IOException {
    boolean initialEntry = true;
    StringBuilder sb = new StringBuilder();
    for (String value : values) {
      if (!initialEntry) {
        sb.append(CSVSEPARATOR);
      }
      sb.append(followCVSformat(value));

      initialEntry = false;
    }
    sb.append("\n");
    w.append(sb.toString());
  }
}
