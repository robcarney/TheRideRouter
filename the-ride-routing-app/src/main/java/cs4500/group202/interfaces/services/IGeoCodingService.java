package cs4500.group202.interfaces.services;

import cs4500.group202.interfaces.model.ILatLong;

/**
 * For the purposes of geocoding addresses into lat/long.
 */
public interface IGeoCodingService {

  /**
   * Retrieves corresponding LatLong for a given address.
   * @param address String address to be geocoded
   * @return Corresponding lat/long combo
   */
  ILatLong getLatLongWithGeocode(String address);

}
