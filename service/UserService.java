import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String USERS_FILE = "users.txt";

    public boolean register(String username, String password) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return false;
            }
        }
        User newUser = new User(username, password);
        FileDatabase.appendLine(USERS_FILE, newUser.toFileString());
        System.out.println("User registered successfully.");
        return true;
    }

    public boolean login(String username, String password) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private List<User> getAllUsers() {
        List<String> lines = FileDatabase.readLines(USERS_FILE);
        List<User> users = new ArrayList<>();
        for (String l : lines) {
            User u = User.fromFileString(l);
            if (u != null) {
                users.add(u);
            }
        }
        return users;
    }
}
