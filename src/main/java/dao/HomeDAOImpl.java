package dao;

import model.Home;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactory;

import java.util.List;

public class HomeDAOImpl implements HomeDAO {
    @Override
    public List<Home> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Home> homes = (List<Home>)session.createQuery("FROM Home").list();
        if (!homes.isEmpty()) {
            for (Home home : homes) {
                Hibernate.initialize(home.getDevices());
            }
        }
        session.close();
        return homes;
    }

    @Override
    public Home findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Home home = session.get(Home.class, id);
        if (home != null) {
            Hibernate.initialize(home.getDevices());
        }
        session.close();
        return home;
    }

    @Override
    public void save(Home home) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(home);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Home home) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(home);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Home home) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(home);
        transaction.commit();
        session.close();
    }
}
