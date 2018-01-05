package cs4500.group202.data.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the TripEntity class.
 */
public class TripEntityTest extends TestCase {

  private TripEntity tripEntity = new TripEntity();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite(TripEntityTest.class);
  }

  public void testGetSetTripId()  {
    tripEntity.setTripId(1);
    assertEquals(1, tripEntity.getTripId());
  }

  public void testGetSetAnchor()  {
    tripEntity.setAnchor("Pickup");
    assertEquals("Pickup", tripEntity.getAnchor());
  }

  public void testGetSetCompanion()  {
    tripEntity.setCompanion(true);
    assertTrue(tripEntity.isCompanion());
  }

  public void testGetSetDropOff()  {
    AddressEntity dropoff = new AddressEntity();
    tripEntity.setDropoffAddress(dropoff);
    assertEquals(dropoff, tripEntity.getDropoffAddress());
  }

  public void testGetSetPickUp()  {
    AddressEntity pickup = new AddressEntity();
    tripEntity.setPickupAddress(pickup);
    assertEquals(pickup, tripEntity.getPickupAddress());
  }

  public void testGetSetServiceAnimal() {
    tripEntity.setServiceAnimal(true);
    assertTrue(tripEntity.getServiceAnimal());
  }

  public void testGetSetPcas()  {
    tripEntity.setPca(true);
    assertTrue(tripEntity.isPca());
  }

}
