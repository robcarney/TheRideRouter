package cs4500.group202.model;

import cs4500.group202.interfaces.model.ILatLong;

/**
 * For the purposes of representing a lat/long combination.
 */
public class LatLong implements ILatLong {

  private final double latitude;

  private final double longitude;

  public LatLong(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public double getLatitude() {
    return this.latitude;
  }

  @Override
  public double getLong() {
    return this.longitude;
  }
}
