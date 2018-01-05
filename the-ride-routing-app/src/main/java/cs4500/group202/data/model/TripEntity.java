package cs4500.group202.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * For the purposes of representing a TripEntity in the database.
 */
@Entity(name = "TRIP")
public class TripEntity {

  @Column(name = "TRIP_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tripId;

  @Column(name = "PERSONAL_CARE_ASSISTANT")
  private Boolean pca;

  @Column(name = "COMPANION")
  private Boolean companion;

  @Column(name = "ANCHOR")
  private String anchor;

  @Column(name = "SERVICE_ANIMAL")
  private Boolean serviceAnimal;

  @ManyToOne
  @JoinColumn(name = "PICKUP_ADDRESS_ID")
  private AddressEntity pickupAddress;

  @ManyToOne
  @JoinColumn(name = "DROPOFF_ADDRESS_ID")
  private AddressEntity dropoffAddress;

  public int getTripId() {
    return tripId;
  }

  public void setTripId(int tripId) {
    this.tripId = tripId;
  }

  public boolean isPca() {
    return pca;
  }

  public void setPca(boolean pca) {
    this.pca = pca;
  }

  public boolean isCompanion() {
    return companion;
  }

  public void setCompanion(boolean companion) {
    this.companion = companion;
  }

  public String getAnchor() {
    return anchor;
  }

  public void setAnchor(String anchor) {
    this.anchor = anchor;
  }

  public AddressEntity getPickupAddress() {
    return pickupAddress;
  }

  public void setPickupAddress(AddressEntity pickupAddress) {
    this.pickupAddress = pickupAddress;
  }

  public AddressEntity getDropoffAddress() {
    return dropoffAddress;
  }

  public void setDropoffAddress(AddressEntity dropoffAddress) {
    this.dropoffAddress = dropoffAddress;
  }

  public boolean getServiceAnimal() {
    return serviceAnimal;
  }

  public void setServiceAnimal(boolean serviceAnimal) {
    this.serviceAnimal = serviceAnimal;
  }

  @Override
  public String toString() {
    return "TripEntity{" +
        "tripId=" + tripId +
        ", pca=" + pca +
        ", companion=" + companion +
        ", anchor='" + anchor + '\'' +
        ", pickupAddress=" + pickupAddress +
        ", dropoffAddress=" + dropoffAddress +
        '}';
  }
}

