package cs4500.group202.model;

import cs4500.group202.model.enums.RequestType;
import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.interfaces.model.IRequest;
import org.joda.time.LocalTime;

/**
 * For the purposes of representing a ride request.
 */
public class Request implements IRequest {

  private int requestId;

  private int tripId;

  private boolean subscription;

  private boolean throughAda;

  private RequestType requestType;

  private LocalTime startPickupTimeWindow;

  private LocalTime endPickupTimeWindow;

  private LocalTime startDropOffTimeWindow;

  private LocalTime endDropOffTimeWindow;

  private IAddress pickupAddress;

  private IAddress dropOffAddress;

  private int pcas;

  private int companions;

  private boolean serviceAnimal;

  public Request(int requestId, int tripId, boolean subscription, boolean throughAda,
      RequestType requestType, LocalTime startPickupTimeWindow,
      LocalTime endPickupTimeWindow, LocalTime startDropOffTimeWindow,
      LocalTime endDropOffTimeWindow, IAddress pickupAddress,
      IAddress dropOffAddress, int pcas, int companions, boolean serviceAnimal) {
    this.requestId = requestId;
    this.tripId = tripId;
    this.subscription = subscription;
    this.throughAda = throughAda;
    this.requestType = requestType;
    this.startPickupTimeWindow = startPickupTimeWindow;
    this.endPickupTimeWindow = endPickupTimeWindow;
    this.startDropOffTimeWindow = startDropOffTimeWindow;
    this.endDropOffTimeWindow = endDropOffTimeWindow;
    this.pickupAddress = pickupAddress;
    this.dropOffAddress = dropOffAddress;
    this.pcas = pcas;
    this.companions = companions;
    this.serviceAnimal = serviceAnimal;
  }

  @Override
  public int getRequestId() {
    return requestId;
  }

  @Override
  public int getTripId() {
    return tripId;
  }

  @Override
  public boolean isSubscription() {
    return subscription;
  }

  @Override
  public boolean isThroughAda() {
    return throughAda;
  }

  @Override
  public RequestType getRequestType() {
    return requestType;
  }

  @Override
  public LocalTime getStartPickupTimeWindow() {
    return startPickupTimeWindow;
  }

  @Override
  public LocalTime getEndPickupTimeWindow() {
    return endPickupTimeWindow;
  }

  @Override
  public LocalTime getStartDropOffTimeWindow() {
    return startDropOffTimeWindow;
  }

  @Override
  public LocalTime getEndDropOffTimeWindow() {
    return endDropOffTimeWindow;
  }

  @Override
  public IAddress getPickupAddress() {
    return pickupAddress;
  }

  @Override
  public IAddress getDropOffAddress() {
    return dropOffAddress;
  }

  @Override
  public int getPcas() {
    return pcas;
  }

  @Override
  public int getCompanions() {
    return companions;
  }

  @Override
  public boolean isServiceAnimal() {
    return serviceAnimal;
  }

  @Override
  public String toString() {
    return "Request{" +
        "requestId=" + requestId +
        ", tripId=" + tripId +
        ", subscription=" + subscription +
        ", throughAda=" + throughAda +
        ", requestType=" + requestType +
        ", startPickupTimeWindow=" + startPickupTimeWindow +
        ", endPickupTimeWindow=" + endPickupTimeWindow +
        ", startDropOffTimeWindow=" + startDropOffTimeWindow +
        ", endDropOffTimeWindow=" + endDropOffTimeWindow +
        ", pickupAddress=" + pickupAddress +
        ", dropOffAddress=" + dropOffAddress +
        ", pcas=" + pcas +
        ", companions=" + companions +
        ", serviceAnimal=" + serviceAnimal +
        '}';
  }
}
