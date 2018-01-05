package cs4500.group202.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import cs4500.group202.interfaces.model.ILatLong;
import cs4500.group202.interfaces.services.IGeoCodingService;
import cs4500.group202.model.LatLong;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Geocoding service using the Google Maps API.
 */
@Service
public class GoogleGeocodingService implements IGeoCodingService {

  final static Logger logger =
          LogManager.getLogger(GoogleGeocodingService.class.getName());

  private final GeoApiContext geoApiContext;

  public GoogleGeocodingService() {
    this.geoApiContext = new GeoApiContext.Builder()
        .apiKey("AIzaSyCYchwB5KsgZvqKL1RD-PiQ7bFpGHDiVFg")
        .build();
  }

  public ILatLong getLatLongWithGeocode(String address)  {
    ILatLong latLong = null;
    try {
      GeocodingResult[] results = GeocodingApi.geocode(this.geoApiContext,
          address).await();
      latLong = new LatLong(results[0].geometry.location.lat,
          results[0].geometry.location.lng);
    } catch (Exception ex)  {
      logger.fatal("error on geocoding request",ex);
    }
    return latLong;
  }
}
