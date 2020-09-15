package dao;

import model.Device;

import java.util.List;

public interface DeviceDAO {
    List<Device> findAll();

    Device findById(int id);

    void save(Device device);

    void update(Device device);

    void delete(Device device);
}
