package cs4500.group202.interfaces.services;

import cs4500.group202.interfaces.model.IAddress;
import org.joda.time.Duration;

/**
 * A service for the purposes of estimating the time it takes to travel between two locations.
 */
public interface IDistanceTimeEstimateService {

  /**
   * Gets the travel time between the two given addresses.
   * @param start Starting address
   * @param end   Ending address
   * @return Time as represented by a joda time Duration
   */
  Duration getTravelTime(IAddress start, IAddress end);

}
