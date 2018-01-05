package cs4500.group202.controller;

import cs4500.group202.data.model.UserEntity;
import java.security.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * For the purposes of communicating web requests for the account page.
 */
@Controller
public class AccountController {

  @RequestMapping(value = "/account")
  public String account(Principal principal, Model model)  {
    UserEntity user = (UserEntity) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
    model.addAttribute("username", user.getUsername());

    return "account";
  }

}
