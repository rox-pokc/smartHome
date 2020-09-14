import com.fasterxml.jackson.databind.ObjectMapper;
import exception.UserManagementException;
import model.User;
import service.UserService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserManager {
    private Scanner in = new Scanner(System.in);
    private UserService userService = new UserService();

    public User login() throws UserManagementException {
        System.out.print("Enter login: ");
        String login = in.nextLine();
        System.out.print("Enter password: ");
        String password = in.nextLine();
        return userService.login(login, password);
    }

    public User registration() throws UserManagementException {
        Map<String, String> userFields = userForm();
        try {
            checkValidUserFields(userFields);
        } catch (UserManagementException exception) {
            System.out.println(exception.getMessage());
            registration();
        }
        User user = new User();
        user.setLogin(userFields.get("login"));
        user.setPassword(String.valueOf(userFields.get("password").hashCode()));
        user.setName(userFields.get("name"));
        user.setAge(Integer.parseInt(userFields.get("age")));
        return userService.registration(user);
    }

    public void showUser(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, user);
        System.out.println(writer.toString());
    }

    public User updateUser(User user) throws UserManagementException {
        Map<String, String> userFields = userForm();
        checkValidUserFields(userFields);
        user.setLogin(userFields.get("login"));
        user.setPassword(String.valueOf(userFields.get("password").hashCode()));
        user.setName(userFields.get("name"));
        user.setAge(Integer.parseInt(userFields.get("age")));
        userService.update(user);
        return user;
    }

    public void deleteUser(User user) {
        userService.delete(user);
    }

    private HashMap<String, String> userForm() {
        HashMap<String, String> userFields = new HashMap<>();
        System.out.print("Enter login: ");
        userFields.put("login", in.nextLine());
        System.out.print("Enter password: ");
        userFields.put("password", in.nextLine());
        System.out.print("Enter name: ");
        userFields.put("name", in.nextLine());
        System.out.print("Enter age: ");
        userFields.put("age", in.nextLine());
        return userFields;
    }

    private void checkValidUserFields(Map<String, String> _userFields) throws UserManagementException{
        if (_userFields.get("password").length() < 6) {
            throw new UserManagementException("Password should contain at least 6 symbols!");
        }
        if (Stream.of(_userFields.get("login"), _userFields.get("password"), _userFields.get("name")).anyMatch(""::equals)) {
            throw new UserManagementException("'Login', 'password', 'name' fields cannot be blank!");
        }
        if (!_userFields.get("age").isEmpty() && !_userFields.get("age").matches("-?\\d+(\\.\\d+)?"))
            throw new UserManagementException("'Age' field is numeric!");
        if (_userFields.get("age").isEmpty()) {
            _userFields.put("age", "0");
        }
    }
}
