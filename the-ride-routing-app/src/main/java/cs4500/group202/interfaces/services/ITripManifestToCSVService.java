package cs4500.group202.interfaces.services;

import cs4500.group202.interfaces.model.IManifestEntry;

import java.io.FileWriter;
import java.util.List;

/**
 * A service to convert tripManifest to a CSV file
 */
public interface ITripManifestToCSVService {

    /**
     * format the trip manifest as a csv file
     * @param tripManifest the list of trips
     * @param location the location of the file
     * @return the file locations
     */
    List<String> writeManifestToCSV(List<IManifestEntry> tripManifest, String location) throws Exception;
}
