package cs4500.group202.services;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.state.StateManager;
import com.graphhopper.jsprit.core.algorithm.termination.IterationWithoutImprovementTermination;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem.FleetSize;
import com.graphhopper.jsprit.core.problem.constraint.ConstraintManager;
import com.graphhopper.jsprit.core.problem.job.Shipment;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TimeWindow;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;
import cs4500.group202.interfaces.model.ILatLong;
import cs4500.group202.interfaces.model.IManifestEntry;
import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.interfaces.model.ITripManifest;
import cs4500.group202.interfaces.services.IRideSchedulingService;
import cs4500.group202.model.ManifestEntry;
import cs4500.group202.model.RoutingCostSubClass;
import cs4500.group202.model.TripManifest;
import org.joda.time.LocalTime;

import java.io.File;
import java.util.*;
import org.springframework.stereotype.Service;

/**
 * Ride Scheduling service using Jsprit
 */
@Service
public class RideSchedulingService implements IRideSchedulingService {

  private static int WHEELCHAIRSPACEINDEX = 0;

  private static int PASSENGERSEATSINDEX = 1;

  private static int MINUTESPERHOUR = 60;

  private static double BUSCOSTPERHOUR = 50;

  private static double BUSCOSTPERMINUTE = BUSCOSTPERHOUR / MINUTESPERHOUR;

  private int numBusses = 30;

  @Override
  public void setNumBusses(int numBusses) {
    this.numBusses = numBusses;
  }

  @Override
  public ITripManifest scheduleRides(List<IRequest> requests) {

    createOutputFolder();

    //Create wheelChairBuses
    List<VehicleImpl> busFleet = createBuses(numBusses);

    //Create shipments aka riders from input
    List<Shipment> riders = createRiders(requests);

    //Build the Vehicle routing problem
    VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();

    //Add the buses
    for (int i = 0; i < busFleet.size(); i++) {
      vrpBuilder.addVehicle(busFleet.get(i));
    }

    //Add the riders
    for (int i = 0; i < riders.size(); i++) {
      vrpBuilder.addJob(riders.get(i));
    }

    vrpBuilder.setFleetSize(FleetSize.FINITE);

    vrpBuilder.setRoutingCost(new RoutingCostSubClass());

    //build the problem
    VehicleRoutingProblem problem = vrpBuilder.build();
    StateManager stateManager = new StateManager(problem);
    ConstraintManager constraintManager = new ConstraintManager(problem, stateManager);
    VehicleRoutingAlgorithm algorithm = Jsprit.Builder.newInstance(problem)
        .setStateAndConstraintManager(stateManager, constraintManager).buildAlgorithm();
    algorithm.setPrematureAlgorithmTermination(new IterationWithoutImprovementTermination(100));

        /*
         * and search a solution
		 */
    Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();

		/*
         * get the best
		 */
    VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

    SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);

    return new TripManifest((int) bestSolution.getCost(),
        transformSchedule(bestSolution, requests));

  }

  private List<IManifestEntry> transformSchedule(VehicleRoutingProblemSolution solution,
      List<IRequest> requests) {
    List<IManifestEntry> schedule = new ArrayList<>();
    List<VehicleRoute> list = new ArrayList<VehicleRoute>(solution.getRoutes());
    Map<Integer, Integer> routeMap = new HashMap<>();
    Map<Integer, Long> pickupMap = new HashMap<>();
    Map<Integer, Long> dropoffMap = new HashMap<>();
    int routeNum = 1;
    Collections.sort(list, new com.graphhopper.jsprit.core.util.VehicleIndexComparator());
    for (VehicleRoute route : list) {
      for (TourActivity act : route.getActivities()) {
        String jobId;
        if (act instanceof TourActivity.JobActivity) {
          jobId = ((TourActivity.JobActivity) act).getJob().getId();
          routeMap.put((Integer.parseInt(jobId)), routeNum);
          if (act.getName().equals("pickupShipment")) {
            pickupMap.put(Integer.parseInt(jobId), Math.round(act.getEndTime()));
          } else {
            dropoffMap.put(Integer.parseInt(jobId), Math.round(act.getEndTime()));
          }
        }
      }
      routeNum++;
    }

    for (int i = 0; i < requests.size(); i++) {
      IRequest request = requests.get(i);
      int tripId = request.getTripId();

      int routeId = routeMap.get(tripId);
      LocalTime pickUpTime = longToLocalTime(pickupMap.get(tripId));
      LocalTime dropOffTime = longToLocalTime(dropoffMap.get(tripId));
      schedule.add(new ManifestEntry(tripId, routeId, routeId, pickUpTime, dropOffTime,
          request.getPickupAddress(), request.getDropOffAddress()));
    }

    return schedule;
  }

  private LocalTime longToLocalTime(long minutes) {
    return new LocalTime((int) minutes / 60, (int) minutes % 60);
  }

  /**
   * To create a fleet of buses
   *
   * @return a list of buses
   */
  private List<VehicleImpl> createBuses(int numberOfBuses) {
    VehicleType wheelChairBus = createWheelChairBus();
    List<VehicleImpl> busFleet = new ArrayList<>();
    for (int i = 0; i < numberOfBuses; i++) {
      VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance("wheelchair_bus_" + i);
      //Busses
      vehicleBuilder1.setStartLocation(Location.newInstance(42.3601, -71.0589));
      //lets say the day starts at 5 am
      vehicleBuilder1.setEarliestStart(300);
      vehicleBuilder1.setType(wheelChairBus);
      VehicleImpl bus = vehicleBuilder1.build();
      busFleet.add(bus);
    }
    return busFleet;
  }


  /**
   * To create riders as shipments
   *
   * @param requests the list of trips requested
   * @return the list of riders
   */
  private List<Shipment> createRiders(List<IRequest> requests) {
    List<Shipment> shipments = new ArrayList<>();
    for (int i = 0; i < requests.size(); i++) {
      IRequest request = requests.get(i);
      Shipment.Builder shipBuilder = Shipment.Builder
          .newInstance(String.valueOf(request.getTripId()));
      int nonWheelChairSeats = request.getCompanions() + request.getPcas();

      // this code allows for wheelchair spaces to be allocated
      if (requests.get(i).isThroughAda()) {
        shipBuilder = buildWheelChair(shipBuilder);
      } else {
        nonWheelChairSeats++;
      }
      if (nonWheelChairSeats > 0) {
        shipBuilder.addSizeDimension(PASSENGERSEATSINDEX, nonWheelChairSeats);
      }

      //Add time window
      shipBuilder.addDeliveryTimeWindow(
          TimeWindow.newInstance(timeToDouble(request.getStartPickupTimeWindow()),
              timeToDouble(request.getEndDropOffTimeWindow())));
      shipBuilder.addPickupTimeWindow(
          TimeWindow.newInstance(timeToDouble(request.getStartPickupTimeWindow()),
              timeToDouble(request.getEndPickupTimeWindow())));

      //Add address
      ILatLong pickupLoc = request.getPickupAddress().getLatLong();
      ILatLong dropOffLoc = request.getDropOffAddress().getLatLong();
      shipBuilder
          .setPickupLocation(Location.newInstance(pickupLoc.getLatitude(), pickupLoc.getLong()));
      shipBuilder.setDeliveryLocation(
          Location.newInstance(dropOffLoc.getLatitude(), dropOffLoc.getLong()));

      shipments.add(shipBuilder.build());
    }
    return shipments;
  }

  /**
   * Converts a time to time in minutes
   *
   * @param time a local time
   * @return the time in minutes
   */
  private double timeToDouble(org.joda.time.LocalTime time) {
    return time.getMinuteOfHour() + (time.getHourOfDay() * 60.0);
  }

  /**
   * Add the paramater of needing a wheelchair vehicle to this rider
   *
   * @param sb the rider builder
   * @return the rider builder with the constraint
   */
  private Shipment.Builder buildWheelChair(Shipment.Builder sb) {
    return sb.addSizeDimension(WHEELCHAIRSPACEINDEX, 1);
  }

  /**
   * Create an output folder for the Jsprit algorithm
   */
  private static void createOutputFolder() {
    File dir = new File("output");
    // if the directory does not exist, create it
    if (!dir.exists()) {
      boolean result = dir.mkdir();
    }
  }

  /**
   * Create a wheelChairBus
   *
   * @return a vehicle type wheelChairBus
   */
  private VehicleType createWheelChairBus() {
    VehicleTypeImpl.Builder wheelChairTypeBuilder = VehicleTypeImpl.Builder
        .newInstance("wheelChairBusType")
        .addCapacityDimension(WHEELCHAIRSPACEINDEX, 2) //can transport two people with wheelchair
        .addCapacityDimension(PASSENGERSEATSINDEX, 4)//and 4 without
        .setCostPerWaitingTime(BUSCOSTPERMINUTE)
        .setCostPerTransportTime(BUSCOSTPERMINUTE);
    return wheelChairTypeBuilder.build();
  }
}
