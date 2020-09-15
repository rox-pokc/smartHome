import exception.ExitProgramException;
import model.User;

public class Main {
    ClientSession clientSession = new ClientSession();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
        System.exit(0);
    }

    private void run() {
        System.out.print("Hello! This is app for your smart home devices control.\n");
        System.out.print("Put down a number to choose necessary command.\n");
        try {
            User user = null;
            do {
                user = clientSession.authorize();
            } while (user == null);
            clientSession.session(user);
        } catch (ExitProgramException exception) {
            System.out.println("Good buy!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}