package cs4500.group202.interfaces.model;

import cs4500.group202.model.enums.RequestType;
import org.joda.time.LocalTime;

/**
 * For the purposes of representing a request.
 */
public interface IRequest {

  /**
   * Gets unique request ID for this request.
   * @return Request ID
   */
  int getRequestId();

  /**
   * Gets local trip ID for this request.
   * @return TripEntity ID
   */
  int getTripId();

  /**
   * Tells whether this request is from a subscription account.
   * @return True if a subscription account, false otherwise
   */
  boolean isSubscription();

  /**
   * Tells whether this trip is through the ADA.
   * @return True if it is through ADA, false otherwise.
   */
  boolean isThroughAda();

  /**
   * Retrieves request type for this request (PICKUP or DROPOFF).
   * @return Request type
   */
  RequestType getRequestType();

  /**
   * Gets the start of the pickup time window.
   * @return Time representing start of pickup time window
   */
  LocalTime getStartPickupTimeWindow();

  /**
   * Gets the end of the pickup time window.
   * @return Time representing end of pickup time window
   */
  LocalTime getEndPickupTimeWindow();

  /**
   * Gets the start of the drop off time window.
   * @return Time representing start of drop off time window
   */
  LocalTime getStartDropOffTimeWindow();

  /**
   * Gets the end of the drop off time window.
   * @return Time representing end of drop off time window
   */
  LocalTime getEndDropOffTimeWindow();

  /**
   * Gets the pickup address of this request.
   * @return Pickup address
   */
  IAddress getPickupAddress();

  /**
   * Gets the dropoff address of this request.
   * @return Dropoff address
   */
  IAddress getDropOffAddress();

  /**
   * Gets number of PCA's for this request.
   * @return Number of PCA's
   */
  int getPcas();

  /**
   * Gets number of companions for this request.
   * @return Number of companions
   */
  int getCompanions();

  /**
   * Tells whether this request has a service animal.
   * @return True if there is a service animal, false otherwise
   */
  boolean isServiceAnimal();

}
