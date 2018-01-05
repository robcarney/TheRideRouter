package cs4500.group202;


import cs4500.group202.interfaces.model.IAddress;
import cs4500.group202.model.Address;
import cs4500.group202.model.LatLong;
import cs4500.group202.services.DistanceTimeEstimateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Central class for running the application.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "cs4500.group202")
public class TheRideApp extends SpringBootServletInitializer {

  public static void main(String[] args) {
    IAddress address1 = new Address(1, 231, "Savin Hill Ave.", "Boston",
        "02125", new LatLong(42.309085, -71.046711));
    IAddress address2 = new Address(2, 5, "St. James Pl.", "Boston",
        "02125", new LatLong(42.349831, -71.073061));
    SpringApplication.run(TheRideApp.class, args);
  }

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder)  {
    return builder.sources(TheRideApp.class);
  }
}
