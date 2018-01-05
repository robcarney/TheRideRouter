package cs4500.group202.config;

import cs4500.group202.controller.util.FlashMessage;
import cs4500.group202.controller.util.FlashMessage.Status;
import cs4500.group202.interfaces.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * For the purposes of configuring security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private IUserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web)  {
    web.ignoring().antMatchers("/assets/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .anyRequest().hasRole("USER")
          .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .successHandler(loginSuccessHandler())
          .failureHandler(loginFailureHandler())
          .and()
        .logout()
          .permitAll()
          .logoutSuccessUrl("/login");
  }

  @Bean
  public PasswordEncoder passwordEncoder()  {
    return new BCryptPasswordEncoder(10);
  }

  public AuthenticationSuccessHandler loginSuccessHandler()  {
    return (request, response, application) -> {
      response.sendRedirect("/");
    };
  }

  public AuthenticationFailureHandler loginFailureHandler()  {
    return (request, response, exception) -> {
      request.getSession().setAttribute("flash", new FlashMessage("Invalid login",
          Status.FAILURE));
      response.sendRedirect("/login");
    };
  }


}
