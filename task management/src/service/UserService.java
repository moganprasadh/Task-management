package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}
