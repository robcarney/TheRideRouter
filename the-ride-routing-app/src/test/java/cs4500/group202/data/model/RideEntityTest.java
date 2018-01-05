package cs4500.group202.data.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the RideEntity class.
 */
public class RideEntityTest extends TestCase {

  RideEntity ride = new RideEntity();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(RideEntityTest.class);
  }

  public void testGetSetRideId()  {
    ride.setRideId(45);
    assertEquals(45, ride.getRideId());
  }

  public void testGetSetLedger()  {
    LedgerEntity ledger = new LedgerEntity();
    ride.setLedger(ledger);
    assertEquals(ledger, ride.getLedger());
  }

  public void testGetSetTrip() {
    TripEntity trip = new TripEntity();
    ride.setTrip(trip);
    assertEquals(trip, ride.getTrip());
  }

  public void testGetSetVehicle() {
    VehicleEntity vehicleEntity = new VehicleEntity();
    ride.setVehicle(vehicleEntity);
    assertEquals(vehicleEntity, ride.getVehicle());
  }



}
