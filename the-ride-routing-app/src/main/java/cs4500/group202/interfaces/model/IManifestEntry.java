package cs4500.group202.interfaces.model;

import org.joda.time.LocalTime;

/**
 * An entry in a trip manifest
 */
public interface IManifestEntry {


    /**
     * Gets local trip ID for this request.
     * @return Trip ID
     */
    int getTripId();


    /**
     * gets the vehicleID
     * @return the id of the vehicle within the system
     */
    int getVehicleId();


    /**
     * gets the route number
     * @return the route number
     */
    int getRideID();


    /**
     * Gets the pickup.
     * @return Time representing pickup time
     */
    LocalTime getPickupTime();


    /**
     * Gets the drop off time.
     * @return Time representing drop off time
     */
    LocalTime getDropOffTime();


    /**
     * Gets the pickup address of this request.
     * @return Pickup address
     */
    IAddress getPickupAddress();

    /**
     * Gets the dropoff address of this request.
     * @return Dropoff address
     */
    IAddress getDropOffAddress();

}
