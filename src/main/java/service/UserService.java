package service;

import dao.UserDAOImpl;
import exception.UserManagementException;
import model.User;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService() {}

    public User login(String login, String password) throws UserManagementException {
        User user = userDAO.findByCredentials(login, String.valueOf(password.hashCode()));
        if (user != null) {
            return user;
        }
        throw new UserManagementException("Wrong credentials!");
    }

    public User registration(User user) throws UserManagementException {
        if (userDAO.findByLogin(user.getLogin()) != null) {
            throw new UserManagementException("User with such login already exist!");
        }
        userDAO.save(user);
        return user;
    }

    public void update(User user) throws UserManagementException {
        if (userDAO.findUserWithSimilarLogin(user) != null) {
            throw new UserManagementException("User with such login already exist!");
        }
        userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }
}
