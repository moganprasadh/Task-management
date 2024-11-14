package dao;

import model.User;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int userId);
    void deleteUser(int userId);
}
