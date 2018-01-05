package cs4500.group202.services;

import cs4500.group202.interfaces.services.IUserService;
import cs4500.group202.mocks.UserRepositoryMock;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * For the purposes of testing the UserService class.
 */
public class UserServiceTest extends TestCase {

  private IUserService userService = new UserService(new UserRepositoryMock());

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite(UserServiceTest.class);
  }

  public void testLoadByUsernameFound()  {
    assertNotNull(userService.loadUserByUsername("Rob"));
  }

  public void testLoadByUsernameException()  {
    try  {
      userService.loadUserByUsername("other");
    } catch (UsernameNotFoundException ex)  {
      assertTrue(true);
      return;
    }
    assertTrue(false);
  }

  public void testFindByUsername()  {
    assertNotNull(userService.findByUsername("Rob"));
  }
}
