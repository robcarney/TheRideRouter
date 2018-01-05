package cs4500.group202.data.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * For the purposes of testing the RoleEntity class.
 */
public class RoleEntityTest extends TestCase{

  private RoleEntity roleEntity = new RoleEntity();

  public static Test suite() {
    return new TestSuite(RoleEntityTest.class);
  }

  public void testGetSetName()  {
    String name = "ROLE_USER";
    roleEntity.setName(name);
    assertEquals(name, roleEntity.getName());
  }

  public void testGetSetId()  {
    Long id = new Long(123);
    roleEntity.setId(id);
    assertEquals(id, roleEntity.getId());
  }

}
