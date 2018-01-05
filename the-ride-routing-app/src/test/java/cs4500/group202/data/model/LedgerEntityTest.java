package cs4500.group202.data.model;

import java.util.Date;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the LedgerEntity class.
 */
public class LedgerEntityTest extends TestCase {

  private LedgerEntity ledger = new LedgerEntity();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(RideEntityTest.class);
  }

  public void testGetSetLedgerId()  {
    ledger.setLedgerId(13);
    assertEquals(13, ledger.getLedgerId());
  }

  public void testGetSetDate()  {
    Date d = new Date();
    ledger.setDate(d);
    assertEquals(d, ledger.getDate());
  }

  public void testGetSetTrip() {
    ledger.setDedicated(true);
    assertTrue(ledger.getDedicated());
  }

}
