package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findByCredentials(String login, String password) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User WHERE login = :login AND password = :password")
                .setParameter("login", login)
                .setParameter("password", password);
        List<User> users = (List<User>)query.list();
        session.close();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByLogin(String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User WHERE login = :login")
                .setParameter("login", login);
        List<User> users = (List<User>)query.list();
        session.close();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findUserWithSimilarLogin(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User WHERE login = :login AND id != :id")
                .setParameter("login", user.getLogin())
                .setParameter("id", user.getId());
        List<User> users = (List<User>)query.list();
        session.close();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
