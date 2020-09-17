package dao;

import model.User;

public interface UserDAO {
    User findByLogin(String login);

    User findByCredentials(String login, String password);

    User findUserWithSimilarLogin(User user);

    void save(User user);

    void update(User user);

    void delete(User user);
}
