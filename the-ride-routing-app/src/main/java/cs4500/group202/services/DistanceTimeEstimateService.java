package cs4500.group202.services;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.ILatLong;
import cs4500.group202.interfaces.services.IDistanceTimeEstimateService;
import cs4500.group202.model.Address;
import cs4500.group202.model.Request;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DistanceTimeEstimateService implements IDistanceTimeEstimateService {

  //Method to get the estimated travel time using manhatten distance from two latLong points
  public Duration getTravelTime(IAddress start, IAddress end){
    int milesPerLat = 69;
    int milesPerLong = 83;

    //get the latitude and longetude points out of the two addresses
    ILatLong LatLongStart = start.getLatLong();
    ILatLong LatLongEnd = end.getLatLong();
    double LatStart = LatLongStart.getLatitude();
    double LongStart = LatLongStart.getLong();
    double LatEnd = LatLongEnd.getLatitude();
    double LongEnd = LatLongEnd.getLong();

    //get the distances between the two latitude and longeude points
    double LatDiff;
    double LongDiff;
    if (LatStart > LatEnd){
      LatDiff = LatStart - LatEnd;
    } else {
      LatDiff = LatEnd - LatStart;
    }
    if (LongStart > LongEnd){
      LongDiff = LongStart - LongEnd;
    } else {
      LongDiff = LongEnd - LongStart;
    }

    //the size of earths radius in miles
    double R = 3959;
    //haversine formula to get distances

    //get latitude distance in miles
    double LatSin = Math.sin(LatDiff / 2);
    double LatA = LatSin * LatSin;
    double LatC = 2 * Math.atan(Math.sqrt(LatA));
    double LatDistanceInMiles =  milesPerLat * LatDiff;

    //get longitude distance in miles
    double LongSin = Math.sin(LongDiff / 2);
    double LongA = LongSin * LongSin;
    double LongC = 2 * Math.atan(Math.sqrt(LongA));
    double LongDistanceInMiles =  milesPerLong * LongDiff;

    //add distances together to get total distance
    double TotalDistance = LongDistanceInMiles + LatDistanceInMiles;
    double timeInSeconds = (TotalDistance / 25) * 3600;

    //assuming average speed of 35mph
    return Duration.standardSeconds((long) timeInSeconds);


  }

}
