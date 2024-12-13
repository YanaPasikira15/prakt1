import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class User {
    private int id;
    private String name;
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
public class OptionalExample {
    public static void main(String[] args) {
        // Створюємо список користувачів
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Іван"));
        users.add(new User(2, "Марія"));
        users.add(new User(3,  "Яна"));
        // ID для пошуку
        int userIdToFind = 1; // Змінити ID для перевірки
        // Пошук користувача
        Optional<User> foundUser = findUserById(users, userIdToFind);
        foundUser.ifPresentOrElse(
                user -> System.out.println("Користувач знайдений: " + user),
                () -> System.out.println("Користувача з ID " + userIdToFind + " не знайдено.")
        );
    }
    public static Optional<User> findUserById(List<User> users, int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}