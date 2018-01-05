package cs4500.group202.data.repositories;

import cs4500.group202.data.model.UserEntity;
import cs4500.group202.interfaces.repositories.IUserRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * For the purposes of retrieving user data from the database.
 */
@Component
public class UserRepository implements IUserRepository {

  @Autowired
  SessionFactory sessionFactory;

  UserRepository(SessionFactory sessionFactory)  {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public UserEntity findByUsername(String username) {
    Session session = sessionFactory.openSession();

    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
    criteria.from(UserEntity.class);

    List<UserEntity> users = session.createQuery(criteria).getResultList();

    for (UserEntity user : users)  {
      if (user.getUsername().equals(username))  {
        session.close();
        return user;
      }
    }
    session.close();
    return null;
  }
}
