package cs4500.group202.interfaces.model;

/**
 * For the purposes of representing an address.
 */
public interface IAddress {

  /**
   * Retrieves the unique ID for this address.
   * @return AddressEntity ID
   */
  int getId();

  /**
   * Retrieves the street number for this address.
   * @return AddressEntity street number
   */
  int getStreetNumber();

  /**
   * Retrieves the street name for this address.
   * @return AddressEntity street name
   */
  String getStreetName();

  /**
   * Retrieves the city for this address.
   * @return AddressEntity city
   */
  String getCity();

  /**
   * Retrieves the zip code for this address (as a string).
   * @return AddressEntity zip code
   */
  String getZipCode();

  /**
   * Retrieves the latitudinal and longitudinal coordinates of this address,
   *   as a ILatLong object.
   * @return AddressEntity LatLong
   */
  ILatLong getLatLong();

}
