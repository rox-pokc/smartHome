package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    public User findByCredentials(String login, String password) {
        Query query = HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .createQuery("FROM User WHERE login = :login AND password = :password")
                .setParameter("login", login)
                .setParameter("password", password);
        List<User> users = (List<User>)query.list();
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByLogin(String login) {
        Query query = HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .createQuery("FROM User WHERE login = :login")
                .setParameter("login", login);
        List<User> users = (List<User>)query.list();
        return users.isEmpty() ? null : users.get(0);
    }

    public User findUserWithSimilarLogin(User user) {
        Query query = HibernateSessionFactory
                .getSessionFactory()
                .openSession()
                .createQuery("FROM User WHERE login = :login AND id != :id")
                .setParameter("login", user.getLogin())
                .setParameter("id", user.getId());
        List<User> users = (List<User>)query.list();
        return users.isEmpty() ? null : users.get(0);
    }

    public void save(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
