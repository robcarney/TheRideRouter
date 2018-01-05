package cs4500.group202.services;


import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.interfaces.model.ITripManifest;
import cs4500.group202.interfaces.services.IRideSchedulingService;
import cs4500.group202.model.Address;
import cs4500.group202.model.LatLong;
import cs4500.group202.model.Request;
import cs4500.group202.model.enums.RequestType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For the purposes of testing the ride scheduling service
 */
public class RideSchedulingServiceTest extends TestCase {

    private IRideSchedulingService rideSchedulingService = new RideSchedulingService();
    List<IRequest> req = new ArrayList<>();

    private void initialize() {
        req = new ArrayList<>();
        IRequest req0 = new Request(0, 1, false, true,
                RequestType.PICKUP, new LocalTime(15, 0),
                new LocalTime(15, 15), new LocalTime(16, 15),
                new LocalTime(16, 30), new Address(1, 149, "SUTHERLAND RD",
                "brighton", "02135", new LatLong(42.341039, -71.146916)),
                new Address(2, 63, "EVERETT ST",
                        "allston", "02134", new LatLong(42.355761, -71.138269)),
                1, 0, false);
        IRequest req1 = new Request(0, 2, false, true,
                RequestType.PICKUP, new LocalTime(17, 30),
                new LocalTime(17, 45), new LocalTime(18, 15),
                new LocalTime(18, 30),
                new Address(3, 63, "EVERETT ST",
                        "allston", "02134", new LatLong(42.355761, -71.138269)),
                new Address(4, 66, "SUTHERLAND RD",
                        "brighton", "02135", new LatLong(42.341039, -71.146916)),
                1, 0, false);
        IRequest req2 = new Request(0, 3, true, true,
                RequestType.PICKUP, new LocalTime(7, 30),
                new LocalTime(7, 45), new LocalTime(8, 45),
                new LocalTime(9, 0),
                new Address(5, 10, "CORINNE RD",
                        "brighton", "02135", new LatLong(42.355720, -71.159488)),
                new Address(6, 3, "City Sq",
                        "charlestown", "02129", new LatLong(42.372024, -71.062484)),
                0, 0, false);
        IRequest req3 = new Request(0, 4, false, true,
                RequestType.PICKUP, new LocalTime(15, 0),
                new LocalTime(15, 15), new LocalTime(16, 15),
                new LocalTime(16, 30), new Address(1, 149, "SUTHERLAND RD",
                "brighton", "02135", new LatLong(42.341039, -71.146916)),
                new Address(2, 63, "EVERETT ST",
                        "allston", "02134", new LatLong(42.355761, -71.138269)),
                1, 0, false);

        req.add(req0);
        req.add(req1);
        req.add(req2);
        req.add(req3);
    }




    /**
     * @return the suite of test results
     */
    public static Test suite()  {
        return new TestSuite( RideSchedulingServiceTest.class );
    }


    /**
     * test ScheduleRides
     * @throws Exception
     */
    public void testScheduleRides() throws Exception {
        initialize();
        //a map of trip id's to requests for testing purposes
        Map<Integer, IRequest> idToReq = new HashMap<>();
        for (IRequest request : req) {
            idToReq.put(request.getTripId(), request);
        }
        ITripManifest tripManifest = rideSchedulingService.scheduleRides(req);
        List<IManifestEntry> trips = tripManifest.getTrips();
        //every trip gets scheduled
        assertTrue(trips.size() == req.size());
        //the cost is less than the hourly rate over 5:00 - 18:30
        assertTrue(tripManifest.getCost() < 675);
        //each trip is picked up on time and dropped off before constraints
        for (int i = 0; i < trips.size(); i++) {
            IManifestEntry trip = trips.get(i);
            IRequest correspondingRequest = idToReq.get(trip.getTripId());
            LocalTime pickupTime = trip.getPickupTime();
            LocalTime dropOffTime = trip.getDropOffTime();
            assertTrue(pickupTime.isEqual(correspondingRequest.getStartPickupTimeWindow())
                    || pickupTime.isEqual(correspondingRequest.getEndPickupTimeWindow())
                    || (pickupTime.isAfter(correspondingRequest.getStartPickupTimeWindow())
                    && pickupTime.isBefore(correspondingRequest.getEndPickupTimeWindow())));
            assertTrue(dropOffTime.isEqual(correspondingRequest.getEndDropOffTimeWindow())
                    || dropOffTime.isBefore(correspondingRequest.getEndDropOffTimeWindow()));
        }
    }

}



