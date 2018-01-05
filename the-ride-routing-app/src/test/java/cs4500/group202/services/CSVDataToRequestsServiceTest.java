package cs4500.group202.services;

import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.interfaces.services.ICSVDataToRequestsService;
import cs4500.group202.mocks.DistanceTimeEstimateServiceMock;
import cs4500.group202.mocks.GeocodingServiceMock;
import java.io.StringReader;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the CSV Reader for request conversion.
 */
public class CSVDataToRequestsServiceTest extends TestCase {

  private ICSVDataToRequestsService csvService = new CSVDataToRequestsService(
      new GeocodingServiceMock(), new DistanceTimeEstimateServiceMock()
  );


  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite( CSVDataToRequestsServiceTest.class );
  }

  public void testCSVReader1() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("Trip ID,Subscription (Y or N),ADA/Non,Anchor,RequestTime,PCAs,Companions,"
        + "ServiceAnimal,PickHouseNumber,PickAddress1,Pickcity,pickzip,DropHouseNumber,"
        + "DropAddress1,Dropcity,DropZip\n");
    s.append("1,N,ADA,P,3:00 PM,1,0,N,149,SUTHERLAND RD,BRIGHTON,2135,63,EVERETT ST,"
        + "ALLSTON,2134\n");
    List<IRequest> result = csvService.readDataToRequests(new StringReader(s.toString()));
    assertTrue(result.size() > 0);
    IRequest request1 = result.get(0);
    assertEquals("02135", request1.getPickupAddress().getZipCode());
    assertEquals(63, request1.getDropOffAddress().getStreetNumber());
  }

  public void testCSVReader2() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("Trip ID,Subscription (Y or N),ADA/Non,Anchor,RequestTime,PCAs,Companions,"
        + "ServiceAnimal,PickHouseNumber,PickAddress1,Pickcity,pickzip,DropHouseNumber,"
        + "DropAddress1,Dropcity,DropZip\n");
    s.append("7,Y,ADA,A,8:30 AM,0,0,N,33,DEWEY ST,WATERTOWN,2472,1011,HARRISON AVE,ROXBURY,2119\n");
    s.append("8,Y,ADA,P,2:30 PM,0,0,N,1017,HARRISON AVE,ROXBURY,2119,39,DEWEY ST,WATERTOWN,2472\n");
    List<IRequest> result = csvService.readDataToRequests(new StringReader(s.toString()));
    assertTrue(result.size() == 2);
    IRequest request1 = result.get(0);
    IRequest request2 = result.get(1);
    assertEquals("02472", request1.getPickupAddress().getZipCode());
    assertEquals(1017, request2.getPickupAddress().getStreetNumber());
  }

  public void testCSVReader3() throws Exception {
    StringBuilder s = new StringBuilder();
    // Imporperly formatted
    s.append("Trip ID,Subscription (Y or N),ADA/Non,Anchor,RequestTime,PCAs,Companions,"
        + "ServiceAnimal,PickHouseNumber,PickAddress1,Pickcity,pickzip,DropHouseNumber,"
        + "DropAddress1\n");
    s.append("7,Y,ADA,A,8:30 AM,0,0,N,33,DEWEY ST,WATERTOWN,2472,1011,HARRISON AVE,ROXBURY,2119\n");
    try {
      List<IRequest> result = csvService.readDataToRequests(new StringReader(s.toString()));
    } catch (IllegalArgumentException ex)  {
      assertTrue(true);
      return;
    }
    assertTrue(false);
  }




}
