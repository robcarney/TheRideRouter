package cs4500.group202.model;

import cs4500.group202.model.enums.RequestType;
import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.mocks.AddressMock;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.joda.time.LocalTime;

/**
 * For the purposes of testing the address class.
 */
public class RequestTest extends TestCase {

  // Mock Addresses for creating the test object
  private IAddress pickupAddress = new AddressMock();
  private IAddress dropoffAddress = new AddressMock();

  private LocalTime startPickUpTimeWindow = new LocalTime(4, 30);
  private LocalTime endPickUpTimeWindow = new LocalTime(4, 45);
  private LocalTime startDropOffTimeWindow = new LocalTime(5, 00);
  private LocalTime endDropOffTimeWindow = new LocalTime(5, 45);

  private int reqId = 12;
  private int tripId = 1;
  private boolean isSubscription = true;
  private boolean throughAda = true;
  private RequestType reqType = RequestType.PICKUP;
  private int pcas = 0;
  private int companions = 0;
  private boolean serviceAnimal = false;

  private IRequest request = new Request(reqId, tripId, isSubscription, throughAda, reqType,
      startPickUpTimeWindow, endPickUpTimeWindow, startDropOffTimeWindow, endDropOffTimeWindow,
      pickupAddress, dropoffAddress, pcas, companions, serviceAnimal);

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite( RequestTest.class );
  }

  public void testGetRequestId()  {
    assertEquals(reqId, request.getRequestId());
  }

  public void testIsSubscription()  {
    assertEquals(isSubscription, request.isSubscription());
  }

  public void testIsThroughAda()  {
    assertEquals(throughAda, request.isThroughAda());
  }

  public void testGetRequestType()  {
    assertEquals(reqType, request.getRequestType());
  }

  public void testGetStartPickUpTimeWindow()  {
    assertEquals(startPickUpTimeWindow, request.getStartPickupTimeWindow());
  }

  public void testGetEndPickupTimeWindow()  {
    assertEquals(endPickUpTimeWindow, request.getEndPickupTimeWindow());
  }

  public void testGetStartDropOffTimeWindow()  {
    assertEquals(startDropOffTimeWindow, request.getStartDropOffTimeWindow());
  }

  public void testGetEndDropOffTimeWindow()  {
    assertEquals(endDropOffTimeWindow, request.getEndDropOffTimeWindow());
  }

  public void testGetPickUpAddress()  {
    assertEquals(pickupAddress, request.getPickupAddress());
  }

  public void testGetDropOffAddress()  {
    assertEquals(dropoffAddress, request.getDropOffAddress());
  }

  public void testGetPCAs()  {
    assertEquals(pcas, request.getPcas());
  }

  public void testGetCompanions()  {
    assertEquals(companions, request.getCompanions());
  }

  public void testIsServiceAnimal()  {
    assertEquals(serviceAnimal, request.isServiceAnimal());
  }
}
