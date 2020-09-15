package service;

import dao.HomeDAOImpl;
import model.Home;

import java.util.List;

public class HomeService {
    private HomeDAOImpl homeDAO = new HomeDAOImpl();

    public HomeService(){}

    public List<Home> findAll() {
        return homeDAO.findAll();
    }

    public Home findById(int id) {
        return homeDAO.findById(id);
    }

    public void create(Home home) {
        homeDAO.save(home);
    }

    public void update(Home home) {
        homeDAO.update(home);
    }

    public void delete(Home home) {
        homeDAO.delete(home);
    }
}
