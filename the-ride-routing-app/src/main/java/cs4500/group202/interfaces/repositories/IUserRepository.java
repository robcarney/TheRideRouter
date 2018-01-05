package cs4500.group202.interfaces.repositories;

import cs4500.group202.data.model.UserEntity;

/**
 * For the purposes of retrieving user data from the database.
 */
public interface IUserRepository {

  UserEntity findByUsername(String username);

}
