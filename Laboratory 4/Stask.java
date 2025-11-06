import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stask {
    public static void main(String[] args) {
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader("input.txt");
            writer = new FileWriter("output.txt");
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
            System.out.println("Файл успешно скопирован!");
        } catch (IOException e) {
            System.out.println("Ошибка при открытии или работе с файлом: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}
