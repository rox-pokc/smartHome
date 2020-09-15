import exception.ExitProgramException;
import exception.UserManagementException;
import manager.DeviceManager;
import manager.HomeManager;
import manager.ItemManager;
import manager.UserManager;
import model.User;

import java.io.IOException;
import java.util.Scanner;

public class ClientSession {
    private Scanner in = new Scanner(System.in);
    private UserManager userManager = new UserManager();
    private HomeManager homeManager = new HomeManager();
    private DeviceManager deviceManager = new DeviceManager();

    public User authorize() throws ExitProgramException {
        User user = null;
        try {
            System.out.print("1 - login\n2 - registration\n0 - exit program\nCommand: ");
            String command = in.nextLine();
            switch (command) {
                case "1":
                    user = userManager.login();
                    break;
                case "2":
                    user = userManager.registration();
                    break;
                case "0":
                    throw new ExitProgramException();
                default:
                    System.out.print("Wrong command!\n");
            }
        } catch (UserManagementException exception) {
            System.out.println(exception.getMessage());
        }
        if (user != null) {
            System.out.println("You have successfully authorized as '" + user.getLogin() + "'.\n");
        }
        return user;
    }

    public void session(User user) throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - manage profile\n2 - manage homes\n3 - manage devices\n0 - exit program\nCommand: ");
        String command = in.nextLine();
        switch (command) {
            case "1":
                manageProfile(user);
                break;
            case "2":
                manageItems(homeManager, "home");
                break;
            case "3":
                manageItems(deviceManager, "device");
                break;
            case "0":
                throw new ExitProgramException();
            default:
                System.out.print("Wrong command!");
        }
        session(user);
    }

    private void manageProfile(User user) throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - view profile\n2 - update profile\n3 - delete profile\n0 - exit program\nCommand: ");
        String command = in.nextLine();
        try {
            switch (command) {
                case "1":
                    userManager.showUser(user);
                    break;
                case "2":
                    user = userManager.updateUser(user);
                    userManager.showUser(user);
                    break;
                case "3":
                    userManager.deleteUser(user);
                    System.out.println("Your user now deleted.\n");
                    throw new ExitProgramException();
                case "0":
                    throw new ExitProgramException();
                default:
                    System.out.print("Wrong command!");
            }
        } catch (UserManagementException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void manageItems(ItemManager itemManager, String itemName) throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - view all " + itemName + "\n" +
                "2 - view certain " + itemName + "\n" +
                "3 - create " + itemName + "\n" +
                "4 - update " + itemName + "\n" +
                "5 - delete " + itemName + "\n" +
                "0 - exit program\n" +
                "Command: ");
        String command = in.nextLine();
        int id;
        try {
            switch (command) {
                case "1":
                    itemManager.listItems();
                    break;
                case "2":
                    id = getRequiredId();
                    itemManager.showItem(id);
                    break;
                case "3":
                    itemManager.showItem(itemManager.createItem());
                    break;
                case "4":
                    id = getRequiredId();
                    itemManager.updateItem(id);
                    itemManager.showItem(id);
                    break;
                case "5":
                    id = getRequiredId();
                    itemManager.deleteItem(id);
                    break;
                case "0":
                    throw new ExitProgramException();
                default:
                    System.out.print("Wrong command!");
            }
        } catch (UserManagementException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private int getRequiredId() throws UserManagementException {
        System.out.println("Id: ");
        String id = in.nextLine();
        if (!id.isEmpty() && !id.matches("-?\\d+(\\.\\d+)?"))
            throw new UserManagementException("'Id' is numeric!");
        return Integer.parseInt(id);
    }
}
