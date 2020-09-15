package manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.UserManagementException;
import model.Home;
import service.HomeService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Scanner;

public class HomeManager implements ItemManager{
    private Scanner in = new Scanner(System.in);
    private HomeService homeService = new HomeService();

    public HomeManager() {}

    public void listItems() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, homeService.findAll());
        System.out.println(writer.toString());
    }

    public void showItem(int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, homeService.findById(id));
        System.out.println(writer.toString());
    }

    public int createItem() throws UserManagementException {
        HashMap<String, String> homeFields = homeForm();
        checkValidHomeFields(homeFields);
        Home home = new Home(homeFields.get("name"));
        homeService.create(home);
        return home.getId();
    }

    public void updateItem(int id) throws UserManagementException {
        Home home = homeService.findById(id);
        HashMap<String, String> homeFields = homeForm();
        checkValidHomeFields(homeFields);
        home.setName(homeFields.get("name"));
        homeService.update(home);
    }

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
