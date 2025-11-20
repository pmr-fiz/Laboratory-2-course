import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FirstTask {
    public static void main(String[] args) {
        try {
            String text = "The price of the product is $19.99 and weight is 25 kg, sale - 3.5%";

            Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(text);

            System.out.println("Найденные числа:");

            while (matcher.find()) {
                System.out.println(matcher.group());
            }

        } catch (PatternSyntaxException e) {
            System.out.println("Ошибка в регулярном выражении: " + e.getDescription());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
