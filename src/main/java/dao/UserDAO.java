package dao;

import model.User;

public interface UserDAO {
    User findByLogin(String login);

    User findByCredentials(String login, String password);

    void save(User user);

    void update(User user);

    void delete(User user);
}
