package cs4500.group202.data.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the TripEntity class.
 */
public class VehicleEntityTest extends TestCase {

  private VehicleEntity vehicleEntity = new VehicleEntity();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite(VehicleEntityTest.class);
  }

  public void testGetSetVehicleId()  {
    vehicleEntity.setVehicleId(1);
    assertEquals(1, vehicleEntity.getVehicleId());
  }

  public void testGetSetLicense()  {
    vehicleEntity.setLicensePlate("ABS23");
    assertEquals("ABS23", vehicleEntity.getLicensePlate());
  }

  public void testGetSetDedicated()  {
    vehicleEntity.setDedicated(true);
    assertTrue(vehicleEntity.getDedicated());
  }

  public void testGetSetDropOff()  {
    vehicleEntity.setHandicapAccessible(false);
    assertTrue(!vehicleEntity.getHandicapAccessible());
  }

  public void testGetSetCostPerHour()  {
    vehicleEntity.setCostPerHour(23.5);
    assertEquals(23.5, vehicleEntity.getCostPerHour());
  }

}
