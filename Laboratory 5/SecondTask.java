import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SecondTask {
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{8,16}$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);

            if (matcher.matches()) {
                System.out.println("Пароль корректен!");
            } else {
                System.out.println("Пароль не соответствует требованиям.");
            }

        } catch (PatternSyntaxException e) {
            System.out.println("Ошибка в регулярном выражении: " + e.getDescription());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
