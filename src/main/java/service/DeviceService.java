package service;

import dao.DeviceDAOImpl;
import model.Device;

import java.util.List;

public class DeviceService {
    private DeviceDAOImpl deviceDAO = new DeviceDAOImpl();

    public DeviceService() {}

    public List<Device> findAll() {
        return deviceDAO.findAll();
    }

    public Device findById(int id) {
        return deviceDAO.findById(id);
    }

    public void create(Device device) {
        deviceDAO.save(device);
    }

    public void update(Device device) {
        deviceDAO.update(device);
    }

    public void delete(Device device) {
        deviceDAO.delete(device);
    }
}
