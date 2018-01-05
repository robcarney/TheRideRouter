package cs4500.group202.model;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.mocks.AddressMock;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.joda.time.LocalTime;


public class ManifestEntryTest extends TestCase{
    // Mock Addresses for creating the test object
    private IAddress pickupAddress = new AddressMock();
    private IAddress dropoffAddress = new AddressMock();

    private LocalTime pickupTime = new LocalTime(4, 30);
    private LocalTime dropOffTime = new LocalTime(5, 00);

    private int rideId = 1;
    private int tripId = 1;
    private int vehicleId = 2;

    private IManifestEntry entry = new ManifestEntry(tripId, vehicleId, rideId,
            pickupTime, dropOffTime, pickupAddress, dropoffAddress);

    public static Test suite()  {
        return new TestSuite( ManifestEntryTest.class );
    }

    public void testGetTripId() { assertEquals(tripId, entry.getTripId()); }


    public void testGetVehicleId() { assertEquals(vehicleId, entry.getVehicleId());}


    public void testGetRideID() { assertEquals(rideId, entry.getRideID()); }

    public void testGetPickupTime() { assertEquals(pickupTime, entry.getPickupTime());}

    public void testGetDropOffTime(){ assertEquals(dropOffTime, entry.getDropOffTime());}


    public void testGetPickupAddress() { assertEquals(pickupAddress, entry.getPickupAddress());}


    public void testGetDropOffAddress() { assertEquals(dropoffAddress, entry.getDropOffAddress());}

}