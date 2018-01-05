package cs4500.group202.model;

import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.cost.AbstractForwardVehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.driver.Driver;
import com.graphhopper.jsprit.core.problem.vehicle.Vehicle;

/**
 * A class to compute the time to get between two points using birds eye distance and an assumed average speed
 */
public class RoutingCostSubClass extends AbstractForwardVehicleRoutingTransportCosts {

    private static final double AVERAGESPEED = 30;

    @Override
    public double getTransportTime(Location location, Location location1, double v, Driver driver, Vehicle vehicle) {
        double distance = distance(location.getCoordinate().getX(), location.getCoordinate().getY(),
                location1.getCoordinate().getX(), location1.getCoordinate().getY());
        return distance / AVERAGESPEED * 60; //60 minutes in an hour
    }

    @Override
    public double getTransportCost(Location location, Location location1, double v, Driver driver, Vehicle vehicle) {

        double cost = getTransportTime(location, location1, v, driver, vehicle);

        if (vehicle != null) {
            //There is no cost for going to the depot or leaving the depot
            if (vehicle.getStartLocation() == location || vehicle.getStartLocation() == location1) {
                return 0;
            }
            cost = cost * vehicle.getType().getVehicleCostParams().perTransportTimeUnit;
        }

        return cost;
    }

    /**
     * A method to calculate distance in miles from coordinates
     * Based off code from:
     * https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
     * by users Rednax and dommer
     * @param lat1 the latitude of the first coordinate
     * @param lon1 the longitude of the first coordinate
     * @param lat2 the latitude of the second coordinate
     * @param lon2 the longitude of the second coordinate
     * @return the distance in miles between the two points
     */
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(degreeToRadian(lat1)) * Math.sin(degreeToRadian(lat2)) + Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) * Math.cos(degreeToRadian(theta));
        dist = Math.acos(dist);
        dist = radianToDegree(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }


    /**
     * This function converts decimal degrees to radians
     * @param deg decimal degree
     * @return radian
     */
    private double degreeToRadian(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * Converts radian units to decimal degrees
     * @param rad radian
     * @return value of input in decimal degree
     */
    private double radianToDegree(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}


