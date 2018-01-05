package cs4500.group202.services;

import cs4500.group202.interfaces.model.ILatLong;
import cs4500.group202.interfaces.services.IGeoCodingService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the Google geocoding service.
 */
public class GoogleGeocodingServiceTest extends TestCase {


  private IGeoCodingService geoCodingService = new GoogleGeocodingService();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite( GoogleGeocodingServiceTest.class );
  }

  /**
   * Making sure the results are mostly accurate.
   */
  public void testGeocoder()  {
    ILatLong result = geoCodingService
        .getLatLongWithGeocode("231 Savin Hill Ave. Boston MA 02125");
    assertTrue(result.getLatitude() > 40. && result.getLong() < -70.);
  }

}
