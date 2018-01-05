package cs4500.group202.model;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.ILatLong;

/**
 * For the purposes of representing an address.
 */
public class Address implements IAddress {

  private final int id;

  private final int streetNumber;

  private final String streetName;

  private final String city;

  private final String zipCode;

  private final ILatLong latLong;

  public Address(int id, int streetNumber, String streetName, String city, String zipCode,
      ILatLong latLong) {
    this.id = id;
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.city = city;
    this.zipCode = zipCode;
    this.latLong = latLong;

  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public int getStreetNumber() {
    return this.streetNumber;
  }

  @Override
  public String getStreetName() {
    return this.streetName;
  }

  @Override
  public String getCity() {
    return this.city;
  }

  @Override
  public String getZipCode() {
    return this.zipCode;
  }

  @Override
  public ILatLong getLatLong() {
    return this.latLong;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.streetNumber);
    sb.append(" " + this.streetName);
    sb.append(" " + this.city);
    sb.append(" " + this.zipCode);

    return sb.toString();
  }
}
