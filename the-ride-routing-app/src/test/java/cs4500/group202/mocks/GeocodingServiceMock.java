package cs4500.group202.mocks;

import cs4500.group202.interfaces.model.ILatLong;
import cs4500.group202.model.LatLong;
import cs4500.group202.interfaces.services.IGeoCodingService;

/**
 * A mock for the geocoding service.
 */
public class GeocodingServiceMock implements IGeoCodingService {



  @Override
  public ILatLong getLatLongWithGeocode(String address) {
    return new LatLong(0., 0.);
  }
}
