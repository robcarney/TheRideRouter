package cs4500.group202.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * For the purposes of representing the VEHICLE table in the database.
 */
@Entity(name = "VEHICLE")
public class VehicleEntity {

  @Column(name = "VEHICLE_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int vehicleId;

  @Column(name = "LICENSE_PLATE")
  private String licensePlate;

  @Column(name = "IS_DEDICATED")
  private Boolean dedicated;

  @Column(name = "IS_HANDICAP_ACCESSIBLE")
  private Boolean handicapAccessible;

  @Column(name = "COST_PER_HOUR")
  private Double costPerHour;

  public int getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public Boolean getDedicated() {
    return dedicated;
  }

  public void setDedicated(Boolean dedicated) {
    this.dedicated = dedicated;
  }

  public Boolean getHandicapAccessible() {
    return handicapAccessible;
  }

  public void setHandicapAccessible(Boolean handicapAccessible) {
    this.handicapAccessible = handicapAccessible;
  }

  public Double getCostPerHour() {
    return costPerHour;
  }

  public void setCostPerHour(Double costPerHour) {
    this.costPerHour = costPerHour;
  }

  @Override
  public String toString() {
    return "VehicleEntity{" +
        "vehicleId=" + vehicleId +
        ", licensePlate='" + licensePlate + '\'' +
        ", dedicated=" + dedicated +
        ", handicapAccessible=" + handicapAccessible +
        ", costPerHour=" + costPerHour +
        '}';
  }
}
