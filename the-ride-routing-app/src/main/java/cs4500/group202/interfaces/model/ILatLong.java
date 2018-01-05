package cs4500.group202.interfaces.model;

/**
 * For the purposes of representing a latitude/longitude combination.
 */
public interface ILatLong {

  /**
   * Retrieves Latitude portion of this LatLong.
   * @return Latitude
   */
  double getLatitude();

  /**
   * Retrieves Longitude portion of this LatLong.
   * @return Longitude
   */
  double getLong();

}
