# Optimizing the RIDE
Section 2 Team 2

# Goal
The MBTA has a door-to-door paratransit service called the RIDE for individuals who are prevented from using some or all forms of public transportation due to disability. The system has recently switched to a new operational model where they pay by a revenue hour model. They have introduced non-dedicated vehicles such as taxis, Ubers, and Lyfts in an effort to reduce both strain on the system and overall cost. These changes have created a need for a new scheduling system for the RIDE.

The goal of this project is to design a system that can read in a daily set of trips and customer information and create an optimized route using dedicated and non-dedicated vehicles.


# Motivation
This project is being worked on for the RIDE in hopes of improving their efficiency in cost for the program. The optimized schedule will both lower overall cost and maximize MBTA's vehicles productivity. In addition, this solution will aid the MBTA in understanding the cost benefits of introducing non-dedicated drivers to their service.

# Hosting
This project is currently hosted on a Wildfly server that can be found here: http://128.31.25.108:8080

# Test account
To log in to the system:
Test username: 202
Test password: team202

# Installation
  # Running locally (UNIX environment)
  To run locally, execute the following commands on the command line

  ```
  >>> cd
  $YOUR_PATH$/202/the-ride-routing-app
  >>> mvn spring-boot:run
  ```
  You must have maven installed on your machine for this, see [here](https://maven.apache.org/download.cgi)

# Tests
```
>>> cd
$YOUR_PATH$/202/the-ride-routing-app
>>> mvn test
```
# SonarQube
This project uses SonarQube to generate reports on test coverage.
Report on test coverage can be found here: http://128.31.24.224:9000/dashboard?id=cs4500.group202%3Athe-ride-routing-app

# API Reference
This project uses Google Maps Geocoding API to determine the exact location(longitude and latitude)
of the pickup and drop off address of the trip.

Open source:
This project uses jspirit, which is an open source tool for solving vehicle routing problems
https://github.com/graphhopper/jsprit

# Next Steps:
 - Scaling: The system should successfully route around ~6500 trips. While the maximum trip numbers
    is not at that number yet, there are plans to increase scalability and improve the algorithm
    to handle that many trips
 - Heatmap: Generates a heatmap of all the pick up and drop off locations based on trips to provide
          a map view of which general area is more commonly requested
 - Past Ledger data: Generates a report of various statistics based on past trip ledgers.
   Example statistics include, but are not limited to, frequency of individual customer and
   popular drop off and pick up locations.
 - Incorporate non-dedicated vehicle scheduling: Currently, the system produces a manifest for dedicated vehicles. In the future, the system should also produce a manifest for non-dedicated vehicles. These non-dedicated vehicles include Uber, Lyft and Verifone.

# Authors
 * Jessica Merritt
 * Nancy Li
 * Robert Carney
 * Feijie Zhou
 * Michael Chisholm
