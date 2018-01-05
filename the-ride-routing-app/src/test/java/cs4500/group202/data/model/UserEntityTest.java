package cs4500.group202.data.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the user entity class.
 */
public class UserEntityTest extends TestCase {

  private UserEntity userEntity = new UserEntity();

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()  {
    return new TestSuite(UserEntityTest.class);
  }

  public void testGetSetId()  {
    Long id = new Long(123);
    userEntity.setId(id);
    assertEquals(id, userEntity.getId());
  }

  public void testGetSetUsername()  {
    String username = "Rob";
    userEntity.setUsername(username);
    assertEquals(username, userEntity.getUsername());
  }

  public void testGetSetEnabled()  {
    userEntity.setEnabled(true);
    assertTrue(userEntity.isEnabled());
  }

  public void testGetSetPassword()  {
    String password = "password";
    userEntity.setPassword(password);
    assertEquals(password, userEntity.getPassword());
  }

}
