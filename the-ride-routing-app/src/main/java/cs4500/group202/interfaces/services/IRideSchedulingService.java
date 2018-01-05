package cs4500.group202.interfaces.services;

import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.interfaces.model.ITripManifest;

import java.util.List;

/** For the purpose of scheduling rides
 */
public interface IRideSchedulingService {

    /**
     * Schedules a set of rides
     * @param requests the requested rides
     * @return the trip manifest
     */
    ITripManifest scheduleRides(List<IRequest> requests);

    /**
     * Sets the number of busses to use
     * @param numBusses Number of busses
     */
    void setNumBusses(int numBusses);

}
