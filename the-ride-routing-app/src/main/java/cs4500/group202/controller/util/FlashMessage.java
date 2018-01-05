package cs4500.group202.controller.util;

import java.io.Serializable;

/**
 * For the purposes of representing a flash message added to a view (see layout.html).
 */
public class FlashMessage implements Serializable{
  private String message;
  private Status status;

  public FlashMessage(String message, Status status) {
    this.message = message;
    this.status = status;
  }

  /**
   * For the purposes of representing the status of a given status message.
   */
  public static enum Status {
    SUCCESS,
    INFO,
    FAILURE
  }

  /**
   * Retrieves the message for this flash message.
   * @return Message string
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message for this flash message.
   * @param message String the message should be set to
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Retrieves the status of this flash message.
   * @return Status of this flash message
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Sets the status of this flash message.
   * @param status Status this flash message should be set to
   */
  public void setStatus(Status status) {
    this.status = status;
  }
}
