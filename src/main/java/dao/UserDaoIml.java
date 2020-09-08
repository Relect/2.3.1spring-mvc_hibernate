package dao;

import model.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoIml implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoIml.class);

    @Autowired
    SessionFactory sessionFactory;
//    @PersistenceContext
//    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        logger.info("User successfully saved. User details: "+ user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        logger.info("User successfully updated. User details: " + user);
    }

    @Override
    public void removeUserById(long id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }

    @Override
    public User getUserById(long id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        logger.info("User successfully loaded. User details: " + user);
        return user;

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        List<User> users = query.getResultList();
//        List <User> users = new ArrayList<>();
//        users = (List<User>) entityManager.f;
        for (User user: users) {
            logger.info("User list: " + user);
        }
//        String q1 = "User.getAll";
//        String q2 ="SELECT * FROM users";
//            TypedQuery<User> namedQuery = entityManager.createNamedQuery(q2, User.class);
//        String q4 = "SELECT * FROM users";
//            users = (List<User>)entityManager.createNativeQuery(q4, User.class )
//                .getResultList();
//        users =  namedQuery.getResultList();

        return users;
    }
}
