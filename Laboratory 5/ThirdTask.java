import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ThirdTask {
    public static void main(String[] args) {
        try {
            String text = "Here is aText with eXamples and something like bA or correct PA";

            Pattern pattern = Pattern.compile("([a-z][A-Z])");
            Matcher matcher = pattern.matcher(text);

            String result = matcher.replaceAll("!$1!");

            System.out.println("Исходный текст:");
            System.out.println(text);

            System.out.println("Результат:");
            System.out.println(result);

        } catch (PatternSyntaxException e) {
            System.out.println("Ошибка в регулярном выражении: " + e.getDescription());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
