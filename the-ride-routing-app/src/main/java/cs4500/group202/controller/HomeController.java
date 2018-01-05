package cs4500.group202.controller;


import cs4500.group202.controller.model.FileResult;
import cs4500.group202.controller.util.FlashMessage;
import cs4500.group202.controller.util.FlashMessage.Status;
import cs4500.group202.data.model.UserEntity;
import cs4500.group202.interfaces.model.IRequest;
import cs4500.group202.interfaces.model.ITripManifest;
import cs4500.group202.interfaces.services.ICSVDataToRequestsService;
import cs4500.group202.interfaces.services.IRideSchedulingService;
import cs4500.group202.interfaces.services.ITripManifestToCSVService;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Homepage controller for the application.
 */
@Controller
public class HomeController {

  @Autowired
  private ICSVDataToRequestsService csvDataToRequestsService;

  @Autowired
  private IRideSchedulingService rideSchedulingService;

  @Autowired
  private ITripManifestToCSVService tripManifestToCSVService;

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String uploadFile(@RequestParam MultipartFile file, @RequestParam int numBus,
      Model model, Principal principal)  {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
      List<IRequest> requests = csvDataToRequestsService.readDataToRequests(br);
      for (IRequest request : requests)  {
        System.out.println(request);
      }
      rideSchedulingService.setNumBusses(numBus);
      System.out.println("Num busses: " + numBus);
      ITripManifest tripManifest = rideSchedulingService.scheduleRides(requests);
      UserEntity user = (UserEntity) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
      String fileName = "route-" + user.getUsername() + "-"
          + DateTime.now().toLocalDate().toString();
      List<String> fileUrls = tripManifestToCSVService.writeManifestToCSV(tripManifest.getTrips(),
          "./output/" + fileName);
      List<FileResult> files = new ArrayList<>();
      for (int i = 0; i < fileUrls.size(); i++) {
        String fileUrl = fileUrls.get(i);

        files.add(new FileResult(i + 1, fileUrl, "Dedicated Vehicle " + i));
      }
      model.addAttribute("files", files);
      model.addAttribute("cost", tripManifest.getCost());
    } catch (Exception ex)  {
      ex.printStackTrace();
      model.addAttribute("flash", new FlashMessage(
          "Something went wrong. Please ensure the .csv data is formatted correctly",
          Status.FAILURE));
      return "upload";
    }
    return "result";
  }

  @RequestMapping(value="download", method = RequestMethod.GET)
  @ResponseBody
  public FileSystemResource downloadFile(String file)  {
    try  {
      System.out.println("Here");
      File vehicleFile = new File("./output/" + file);
      return new FileSystemResource(vehicleFile);
    } catch (Exception ex)  {
      ex.printStackTrace();
    }
    return null;
  }


  @RequestMapping(value = "/")
  public String home()  {
    return "home";
  }

  @RequestMapping(value = "/upload")
  public String uploadPage()  {
    return "upload";
  }

  @RequestMapping(value = "/result")
  public String resultPage()  {
    return "result";
  }

}
