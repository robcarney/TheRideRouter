package cs4500.group202.mocks;

import cs4500.group202.data.model.UserEntity;
import cs4500.group202.interfaces.repositories.IUserRepository;

/**
 * A mock for user repository (for testing purposes).
 */
public class UserRepositoryMock implements IUserRepository {

  @Override
  public UserEntity findByUsername(String username) {
    if (username.equals("Rob"))  {
      return new UserEntity();
    }
    return null;
  }
}
