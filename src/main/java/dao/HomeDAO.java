package dao;

import model.Home;

import java.util.List;

public interface HomeDAO {
    List<Home> findAll();

    Home findById(int id);

    void save(Home home);

    void update(Home home);

    void delete(Home home);
}
