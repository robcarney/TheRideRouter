package cs4500.group202.data.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the address class.
 */
public class AddressEntityTest extends TestCase {

  private AddressEntity addressEntity = new AddressEntity();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(AddressEntityTest.class);
  }

  public void testGetSetId() {
    addressEntity.setAddressId(12);
    assertEquals(12, addressEntity.getAddressId());
  }

  public void testGetSetNumber() {
    addressEntity.setNumber(90);
    assertEquals(90, addressEntity.getNumber());
  }

  public void testGetSetStreetName() {
    addressEntity.setStreetName("Main St.");
    assertEquals("Main St.", addressEntity.getStreetName());
  }

  public void testGetSetCity() {
    addressEntity.setCity("New York");
    assertEquals("New York", addressEntity.getCity());
  }

  public void testGetSetZipcode() {
    addressEntity.setZipcode("02125");
    assertEquals("02125", addressEntity.getZipcode());
  }

  public void testGetSetLatitude() {
    addressEntity.setLatitude(40.);
    assertEquals(40., addressEntity.getLatitude());
  }

  public void testGetSetLongitude() {
    addressEntity.setLongitude(69.);
    assertEquals(69., addressEntity.getLongitude());
  }



}
