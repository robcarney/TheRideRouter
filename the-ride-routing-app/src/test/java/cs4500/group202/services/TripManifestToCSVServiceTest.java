package cs4500.group202.services;

import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.interfaces.services.ITripManifestToCSVService;
import cs4500.group202.model.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.joda.time.LocalTime;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TripManifestToCSVServiceTest extends TestCase {

    /**
     * @return the suite of test results
     */
    public static Test suite()  {
        return new TestSuite( TripManifestToCSVServiceTest.class );
    }


    List<IManifestEntry> trips = new ArrayList<>();

    private void initialize() {
        File file = new File("output/test.csv");
        file.delete();
       trips = new ArrayList<>();
        IManifestEntry trip1 = new ManifestEntry(1, 1, 1,
                new LocalTime(15, 0),
                 new LocalTime(16, 15),
                new Address(1, 149, "SUTHERLAND RD",
                        "brighton", "02135", new LatLong(42.341039, -71.146916)),
                new Address(2, 63, "EVERETT ST",
                        "allston", "02134", new LatLong(42.355761, -71.138269)));
        IManifestEntry trip2 = new ManifestEntry(2, 1, 1,
                new LocalTime(17, 30),
                new LocalTime(18, 30),
                new Address(2, 63, "EVERETT ST",
                        "allston", "02134", new LatLong(42.355761, -71.138269)),
                new Address(1, 149, "SUTHERLAND RD",
                        "brighton", "02135", new LatLong(42.341039, -71.146916)));

        IManifestEntry trip3 = new ManifestEntry(5, 1, 1,
                new LocalTime(7, 30),
                new LocalTime(8, 45),
                new Address(5, 10, "CORINNE RD",
                        "brighton", "02135", new LatLong(42.355720, -71.159488)),
                new Address(6, 3, "City Sq",
                        "charlestown", "02129", new LatLong(42.372024, -71.062484)));
        IManifestEntry trip4 = new ManifestEntry(4, 1, 1,
                new LocalTime(15, 0),
                new LocalTime(16, 15),
                new Address(1, 149, "SUTHERLAND RD",
                        "brighton", "02135", new LatLong(42.341039, -71.146916)),
                new Address(2, 63, "EVERETT ST",
                        "allston", "02134", new LatLong(42.355761, -71.138269)));



        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
    }

    public void testWriteManifestToCSV() throws Exception {
        initialize();
        ITripManifestToCSVService service = new TripManifestToCSVService();
        service.writeManifestToCSV(trips, "output/test");
        File file = new File("output/test-vehicle-1.csv");
        assertTrue(file.exists());
        file.delete();

    }

}