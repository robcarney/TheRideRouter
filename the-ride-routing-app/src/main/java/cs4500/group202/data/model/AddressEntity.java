package cs4500.group202.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * For the purposes of representing the ADDRESS table from the db.
 */
@Entity(name = "ADDRESS")
public class AddressEntity {

  @Column(name = "ADDRESS_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer addressId;

  @Column(name = "NUMBER")
  private Integer number;

  @Column(name = "STREET_NAME")
  private String streetName;

  @Column(name = "CITY")
  private String city;


  @Column(name = "ZIPCODE")
  private String zipcode;

  @Column(name = "LATITUDE")
  private Double latitude;

  @Column(name = "LONGITUDE")
  private Double longitude;

  public int getAddressId() {
    return addressId;
  }

  public void setAddressId(int addressId) {
    this.addressId = addressId;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "AddressEntity{" +
        "addressId=" + addressId +
        ", number=" + number +
        ", streetName='" + streetName + '\'' +
        ", city='" + city + '\'' +
        ", zipcode='" + zipcode + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        '}';
  }
}

