package cs4500.group202.model;

import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.interfaces.model.ITripManifest;
import cs4500.group202.interfaces.services.ITripManifestToCSVService;
import cs4500.group202.services.TripManifestToCSVService;

import java.util.List;

public class TripManifest implements ITripManifest {

    private int cost;
    private List<IManifestEntry> trips;

    public TripManifest(int cost, List<IManifestEntry> trips) {
        this.cost = cost;
        this.trips = trips;
    }

    @Override
    public List<IManifestEntry> getTrips() {
        return this.trips;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public void createCsv(String location) {
        ITripManifestToCSVService csvMaker = new TripManifestToCSVService();
        try {
            csvMaker.writeManifestToCSV(trips, location);
        } catch (Exception e) {
            System.out.print("Issue converting manifest to CSV");
        }
    }


}
