package cs4500.group202.model;

import cs4500.group202.interfaces.model.IAddress;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the AddressEntity class.
 */
public class AddressTest extends TestCase {

  private IAddress address = new Address(1, 1, "Main St.",
      "Boston", "02119", new LatLong(50., 75.));

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite( AddressTest.class );
  }

  public void testGetId()  {
    assertEquals(1, address.getId());
  }

  public void testGetStreetNumber()  {
    assertEquals(1, address.getStreetNumber());
  }

  public void testGetStreetName()  {
    assertEquals("Main St.", address.getStreetName());
  }

  public void testGetZipCode()  {
    assertEquals("02119", address.getZipCode());
  }

  public void testGetCity()  {
    assertEquals("Boston",address.getCity());
  }

  public void testGetLatLong()  {
    assertEquals(50., address.getLatLong().getLatitude());
    assertEquals(75., address.getLatLong().getLong());
  }

  public void testToString() {
    assertEquals("1 Main St. Boston 02119", address.toString());
  }




}
