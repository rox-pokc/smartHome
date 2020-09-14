import exception.ExitProgramException;
import exception.UserManagementException;
import model.User;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Scanner in = new Scanner(System.in);
    private UserManager userManager = new UserManager();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
        System.exit(0);
    }

    private void run() {
        System.out.print("Hello! This is app for your smart home devices control.\n");
        System.out.print("Put down a number to choose necessary command.\n");
        UserManager userManager = new UserManager();
        try {
            User user = authorize();
            session(user);

        } catch (ExitProgramException exception) {
            System.out.println("Good buy!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private User authorize() throws ExitProgramException{
        User user = new User();
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
                    System.out.print("Wrong command!");
            }
        } catch (UserManagementException exception) {
            System.out.println(exception.getMessage());
            authorize();
        }
        System.out.println("You have successfully authorized as '" + user.getLogin() + "'.\n");
        return user;
    }

    private void session(User user) throws IOException, ExitProgramException {
        System.out.println("What would you like to do?\n");
        System.out.println("1 - manage profile\n2 - manage home\n3 - manage devices\n0 - exit program\nCommand: ");
        String command = in.nextLine();
        switch (command) {
            case "1":
                manageProfile(user);
                break;
            case "2":
                System.out.println("home");
                break;
            case "3":
                System.out.println("devices");
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


}