package cs4500.group202.data.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * For the purposes of representing the LEDGER table in the database.
 */
@Entity(name = "LEDGER")
public class LedgerEntity {

  @Column(name = "LEDGER_ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ledgerId;

  @Column(name = "DATE", columnDefinition = "DATE")
  @Temporal(TemporalType.DATE)
  private Date date;

  @Column(name = "IS_DEDICATED")
  private Boolean isDedicated;

  public int getLedgerId() {
    return ledgerId;
  }

  public void setLedgerId(int ledgerId) {
    this.ledgerId = ledgerId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean getDedicated() {
    return isDedicated;
  }

  public void setDedicated(Boolean dedicated) {
    isDedicated = dedicated;
  }

  @Override
  public String toString() {
    return "LedgerEntity{" +
        "ledgerId=" + ledgerId +
        ", date=" + date +
        ", isDedicated=" + isDedicated +
        '}';
  }
}
