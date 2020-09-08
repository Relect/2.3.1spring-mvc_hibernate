package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(long id);
    User getUserById(long id);
    List<User> listUsers();
}
