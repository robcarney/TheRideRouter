package cs4500.group202.model;

import cs4500.group202.interfaces.model.ILatLong;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the LatLong class.
 */
public class LatLongTest extends TestCase {

  ILatLong latLong = new LatLong(50., 75.);

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite(LatLongTest.class);
  }

  public void testGetLat()  {
    assertEquals(50., latLong.getLatitude());
  }

  public void testGetLong()  {
    assertEquals(75., latLong.getLong());
  }

}
