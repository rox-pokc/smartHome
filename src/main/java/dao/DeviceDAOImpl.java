package dao;

import model.Device;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import java.util.List;

public class DeviceDAOImpl implements DeviceDAO {
    @Override
    public List<Device> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Device> devices = (List<Device>)session.createQuery("FROM Device").list();
        session.close();
        return devices;
    }

    @Override
    public Device findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Device device = session.get(Device.class, id);
        session.close();
        return device;
    }

    @Override
    public void save(Device device) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(device);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Device device) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(device);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Device device) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(device);
        transaction.commit();
        session.close();
    }
}
