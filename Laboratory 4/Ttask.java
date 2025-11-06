import java.io.FileWriter;
import java.io.IOException;

public class Ttask {
    public static void log(Exception e) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(e.getClass().getName() + ": " + e.getMessage() + "\n");
        } catch (IOException ioEx) {
            System.out.println("Error while loging: " + ioEx.getMessage());
        }
    }

    public static int parse(String s) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Stroke is not a number: " + s, e);
        }
    }

    public static void main(String[] args) {
        String[] data = {"10", "20", "abc", "30"};

        for (String s : data) {
            try {
                int number = parse(s);
                System.out.println("Number: " + number);
            } catch (CustomNumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
                log(e);
            }
        }
    }
}
