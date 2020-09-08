package dao;

import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoEntityImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User us = entityManager.find(User.class, id);
        entityManager.remove(us);
    }

    @Override
    public User getUserById(long id) {
       return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        List<User> users = (List<User>) entityManager.createNativeQuery("SELECT * FROM 'users' ", User.class).getResultList();
        return users;
//        String query = "from User order by id";
//        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
//        return typedQuery.getResultList();
//        return entityManager.createQuery("select u from User u", User.class).getResultList();



    }
}
