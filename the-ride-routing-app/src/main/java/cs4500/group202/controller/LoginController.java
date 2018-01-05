package cs4500.group202.controller;

import cs4500.group202.data.model.UserEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * For the purposes of handling login web requests.
 */
@Controller
public class LoginController {

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String loginForm(Model model, HttpServletRequest request) {
    model.addAttribute("user", new UserEntity());
    try {
      Object flash = request.getSession().getAttribute("flash");
      model.addAttribute("flash", flash);

      request.getSession().removeAttribute("flash");
    } catch (Exception ex)  {
      // Means there was no flash attribute, so do nothing
    }
    return "login";
  }

  @RequestMapping(path = "/access_denied")
  public String accessDenied()  {
    return "access_denied";
  }

}
