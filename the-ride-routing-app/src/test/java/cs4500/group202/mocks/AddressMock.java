package cs4500.group202.mocks;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.ILatLong;

/**
 * A mock for the IAddress interface.
 */
public class AddressMock implements IAddress {

  @Override
  public int getId() {
    return 0;
  }

  @Override
  public int getStreetNumber() {
    return 0;
  }

  @Override
  public String getStreetName() {
    return null;
  }

  @Override
  public String getCity() {
    return null;
  }

  @Override
  public String getZipCode() {
    return null;
  }

  @Override
  public ILatLong getLatLong() {
    return null;
  }
}
