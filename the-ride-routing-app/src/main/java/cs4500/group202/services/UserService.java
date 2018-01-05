package cs4500.group202.services;

import cs4500.group202.data.model.UserEntity;
import cs4500.group202.interfaces.repositories.IUserRepository;
import cs4500.group202.interfaces.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * For the purposes of user related operations.
 */
@Service
public class UserService implements IUserService {

  UserService(IUserRepository userRepository)  {
    this.userRepository = userRepository;
  }

  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserEntity findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    // Load user if found
    UserEntity user = userRepository.findByUsername(username);
    if (user == null)  {
      throw new UsernameNotFoundException("User not found");
    }

    // Return user object
    return user;
  }
}
