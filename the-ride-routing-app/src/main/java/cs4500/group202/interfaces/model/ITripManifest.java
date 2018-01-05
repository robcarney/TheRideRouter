package cs4500.group202.interfaces.model;

import java.util.List;

public interface ITripManifest {

    /**
     * Gets all the scheduled trips
     * @return the list of trips
     */
    List<IManifestEntry> getTrips();


    /**
     * get cost
     * @return the cost of the trip
     */
    int getCost();

    /**
     *  Create a CSV from the tripManifest
     * @param location the address of the file
     */
    void createCsv(String location);


}
