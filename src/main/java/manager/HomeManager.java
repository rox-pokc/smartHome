package manager;

import exception.UserManagementException;
import model.Home;
import service.HomeService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class HomeManager extends ItemManager {
    private Scanner in = new Scanner(System.in);
    private HomeService homeService = new HomeService();

    public HomeManager() {}

    @Override
    public void listItems() throws IOException {
        writeToConsole(homeService.findAll());
    }

    @Override
    public void showItem(int id) throws IOException {
        writeToConsole(homeService.findById(id));
    }

    @Override
    public int createItem() throws UserManagementException {
        HashMap<String, String> homeFields = homeForm();
        checkValidHomeFields(homeFields);
        Home home = new Home(homeFields.get("name"));
        homeService.create(home);
        return home.getId();
    }

    @Override
    public void updateItem(int id) throws UserManagementException {
        Home home = homeService.findById(id);
        HashMap<String, String> homeFields = homeForm();
        checkValidHomeFields(homeFields);
        home.setName(homeFields.get("name"));
        homeService.update(home);
    }

    @Override
    public void deleteItem(int id) {
        Home home = homeService.findById(id);
        homeService.delete(home);
    }

    private HashMap<String, String> homeForm() {
        HashMap<String, String> homeFields = new HashMap<>();
        System.out.print("Enter name: ");
        homeFields.put("name", in.nextLine());
        return homeFields;
    }

    private void checkValidHomeFields(HashMap<String, String> _homeFields) throws UserManagementException{
        if (_homeFields.get("name").compareTo("") == 0) {
            throw new UserManagementException("'Name' field cannot be blank!");
        }
    }
}
