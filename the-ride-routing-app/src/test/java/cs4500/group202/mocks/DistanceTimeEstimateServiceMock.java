package cs4500.group202.mocks;

import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.services.IDistanceTimeEstimateService;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

/**
 * For the purposes of mocking the distance time estimator.
 */
public class DistanceTimeEstimateServiceMock implements IDistanceTimeEstimateService{

  @Override
  public Duration getTravelTime(IAddress start, IAddress end) {
    return new Duration(1000000000);
  }
}
