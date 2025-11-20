import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FifthTask {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите текст: ");
            String text = scanner.nextLine();
            System.out.print("Введите букву, с которой должны начинаться слова: ");
            String letter = scanner.nextLine();

            if (!letter.matches("[A-Za-z]")) {
                System.out.println("Ошибка: введите одну букву (латиница).");
                return;
            }

            String regex = "\\b" + letter + "\\w*";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            System.out.println("\nСлова, начинающиеся с буквы '" + letter + "':");

            boolean found = false;
            while (matcher.find()) {
                System.out.println(matcher.group());
                found = true;
            }

            if (!found) {
                System.out.println("Таких слов нет.");
            }

        } catch (PatternSyntaxException e) {
            System.out.println("Ошибка в регулярном выражении: " + e.getDescription());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
