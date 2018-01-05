package cs4500.group202.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * For the purposes of representing the RIDE table in the database.
 */
@Entity(name = "RIDE")
public class RideEntity {

  @Column(name = "RIDE_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int rideId;

  @ManyToOne
  @JoinColumn(name = "VEHICLE_ID")
  private VehicleEntity vehicle;

  @OneToOne
  @JoinColumn(name = "TRIP_ID")
  private TripEntity trip;

  @ManyToOne
  @JoinColumn(name = "LEDGER_ID")
  private LedgerEntity ledger;

  public int getRideId() {
    return rideId;
  }

  public void setRideId(int rideId) {
    this.rideId = rideId;
  }

  public VehicleEntity getVehicle() {
    return vehicle;
  }

  public void setVehicle(VehicleEntity vehicle) {
    this.vehicle = vehicle;
  }

  public TripEntity getTrip() {
    return trip;
  }

  public void setTrip(TripEntity trip) {
    this.trip = trip;
  }

  public LedgerEntity getLedger() {
    return ledger;
  }

  public void setLedger(LedgerEntity ledger) {
    this.ledger = ledger;
  }

  @Override
  public String toString() {
    return "Ride{" +
        "rideId=" + rideId +
        ", vehicle=" + vehicle +
        ", trip=" + trip +
        ", ledger=" + ledger +
        '}';
  }
}
