package cs4500.group202.interfaces.services;

import cs4500.group202.data.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * For the purposes of user related operations.
 */
public interface IUserService extends UserDetailsService {

  UserEntity findByUsername(String username);

}
