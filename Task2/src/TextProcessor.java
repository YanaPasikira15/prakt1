import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Читання тексту
        System.out.println("Введіть шлях до файлу або натисніть Enter для введення тексту вручну:");
        String input = scanner.nextLine();
        String text;
        if (!input.isEmpty()) {
            try {
                text = Files.readString(Paths.get(input));
            } catch (IOException e) {
                System.out.println("Не вдалося зчитати файл. Перевірте правильність шляху.");
                return;
            }
        } else {
            System.out.println("Введіть текст:");
            text = scanner.nextLine();
        }
        // Перевірка на порожній текст
        if (text.isEmpty()) {
            System.out.println("Текст не може бути порожнім.");
            return;
        }
        List<String> words = Arrays.stream(text.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄґҐ']", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
        if (words.isEmpty()) {
            System.out.println("У тексті немає слів.");
            return;
        }
        System.out.println("Введіть літеру для фільтрації слів:");
        String filterLetter = scanner.nextLine().toLowerCase();
        if (filterLetter.length() != 1 || !Character.isLetter(filterLetter.charAt(0))) {
            System.out.println("Введіть одну літеру.");
            return;
        }
        List<String> filteredWords = words.stream()
                .filter(word -> word.startsWith(filterLetter))
                .sorted()
                .collect(Collectors.toList());
        long uniqueWordCount = words.stream()
                .distinct()
                .count();
        System.out.println("\nРезультати:");
        System.out.println("Слова, що починаються на '" + filterLetter + "':");
        if (filteredWords.isEmpty()) {
            System.out.println("Немає слів, що відповідають умовам фільтрації.");
        } else {
            filteredWords.forEach(System.out::println);
        }
        System.out.println("\nКількість унікальних слів у тексті: " + uniqueWordCount);
    }
}