import exception.ExitProgramException;
import exception.UserManagementException;
import model.Device;
import model.Home;
import model.User;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Scanner in = new Scanner(System.in);
    private UserManager userManager = new UserManager();
    private HomeManager homeManager = new HomeManager();
    private DeviceManager deviceManager = new DeviceManager();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
        //System.exit(0);
    }

    private void run() {
        System.out.print("Hello! This is app for your smart home devices control.\n");
        System.out.print("Put down a number to choose necessary command.\n");
        try {
            User user = null;
            do {
                user = authorize();
            } while (user == null);
            session(user);

        } catch (ExitProgramException exception) {
            System.out.println("Good buy!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private User authorize() throws ExitProgramException{
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

    private void session(User user) throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - manage profile\n2 - manage homes\n3 - manage devices\n0 - exit program\nCommand: ");
        String command = in.nextLine();
        switch (command) {
            case "1":
                manageProfile(user);
                break;
            case "2":
                manageHomes();
                break;
            case "3":
                manageDevices();
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

    private void manageHomes() throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - view all homes\n2 - view certain home\n3 - create home\n4 - update home\n5 - delete home\n0 - exit program\nCommand: ");
        String command = in.nextLine();
        int id;
        try {
            switch (command) {
                case "1":
                    homeManager.listHomes();
                    break;
                case "2":
                    id = getRequiredId();
                    homeManager.showHome(id);
                    break;
                case "3":
                    Home home = homeManager.createHome();
                    homeManager.showHome(home.getId());
                    break;
                case "4":
                    id = getRequiredId();
                    homeManager.updateHome(id);
                    homeManager.showHome(id);
                    break;
                case "5":
                    id = getRequiredId();
                    homeManager.deleteHome(id);
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

    private void manageDevices() throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - view all devices\n2 - view certain device\n3 - create device\n4 - update device\n5 - delete device\n0 - exit program\nCommand: ");
        String command = in.nextLine();
        int id;
        try {
            switch (command) {
                case "1":
                    deviceManager.listDevices();
                    break;
                case "2":
                    id = getRequiredId();
                    deviceManager.showDevice(id);
                    break;
                case "3":
                    Device device = deviceManager.createDevice();
                    deviceManager.showDevice(device.getId());
                    break;
                case "4":
                    id = getRequiredId();
                    deviceManager.updateDevice(id);
                    deviceManager.showDevice(id);
                    break;
                case "5":
                    id = getRequiredId();
                    deviceManager.deleteDevice(id);
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