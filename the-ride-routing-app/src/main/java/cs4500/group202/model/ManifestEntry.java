package cs4500.group202.model;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.IManifestEntry;
import org.joda.time.LocalTime;

public class ManifestEntry implements IManifestEntry{

    private int tripId;

    private int vehicleId;

    private int rideId;

    private LocalTime pickupTime;

    private LocalTime dropOffTime;

    private IAddress pickupAddress;

    private IAddress dropOffAddress;

    public ManifestEntry(int tripId, int vehicleId, int rideId, LocalTime pickupTime,
                         LocalTime dropOffTime, IAddress pickupAddress, IAddress dropOffAddress) {
        this.tripId = tripId;
        this.vehicleId = vehicleId;
        this.rideId = rideId;
        this.pickupTime = pickupTime;
        this.dropOffTime = dropOffTime;
        this.pickupAddress = pickupAddress;
        this.dropOffAddress = dropOffAddress;
    }

    @Override
    public int getTripId() {
        return tripId;
    }

    @Override
    public int getVehicleId() {
        return vehicleId;
    }

    @Override
    public int getRideID() {
        return rideId;
    }

    @Override
    public LocalTime getPickupTime() {
        return pickupTime;
    }

    @Override
    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    @Override
    public IAddress getPickupAddress() {
        return pickupAddress;
    }

    @Override
    public IAddress getDropOffAddress() {
        return dropOffAddress;
    }
}
