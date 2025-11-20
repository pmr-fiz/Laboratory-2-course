import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FourthTask {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите IP-адрес: ");
            String ip = scanner.nextLine();

            String regex = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}"
                         + "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(ip);

            if (matcher.matches()) {
                System.out.println("IP-адрес корректен!");
            } else {
                System.out.println("Некорректный IP-адрес!");
            }

        } catch (PatternSyntaxException e) {
            System.out.println("Ошибка в регулярном выражении: " + e.getDescription());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
