import java.util.Scanner;

public class TextAnalyzer {
    @FunctionalInterface
    interface WordFinder {
        boolean find(String text, String word);
    }
    @FunctionalInterface
    interface WordReplacer {
        String replace(String text, String targetWord, String replacementWord);
    }
    @FunctionalInterface
    interface WordCounter {
        int count(String text);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordFinder wordFinder = (text, word) -> text.contains(word);
        WordReplacer wordReplacer = (text, targetWord, replacementWord) -> text.replaceAll("\\b" + targetWord + "\\b", replacementWord);
        WordCounter wordCounter = text -> text.split("\\s+").length;
        System.out.println("Введіть текст:");
        String text = scanner.nextLine();
        while (true) {
            System.out.println("\nОберіть операцію:");
            System.out.println("1. Пошук слова");
            System.out.println("2. Замінити слово");
            System.out.println("3. Підрахунок кількості слів");
            System.out.println("4. Вихід");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Введіть слово для пошуку:");
                    String wordToFind = scanner.nextLine();
                    boolean found = wordFinder.find(text, wordToFind);
                    System.out.println(found ? "Слово знайдено!" : "Слово не знайдено!");
                    break;
                case 2:
                    System.out.println("Введіть слово для заміни:");
                    String targetWord = scanner.nextLine();
                    System.out.println("Введіть слово-замінник:");
                    String replacementWord = scanner.nextLine();
                    text = wordReplacer.replace(text, targetWord, replacementWord);
                    System.out.println("Оновлений текст: " + text);
                    break;
                case 3:
                    int wordCount = wordCounter.count(text);
                    System.out.println("Кількість слів у тексті: " + wordCount);
                    break;
                case 4:
                    System.out.println("Вихід з програми.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }
        }
    }
}