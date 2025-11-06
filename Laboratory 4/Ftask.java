public class Ftask {
    public static void main(String[] args) {
        Object[] arr = {1, 2, "три", 4, 5};
        int sum = 0;
        try {
            for (int i = 0; i <= arr.length; i++) {
                if (arr[i] instanceof Integer) {
                    sum += (Integer) arr[i];
                } else {
                    throw new NumberFormatException("элемент не является числом: " + arr[i]);
                }
            }
            double average = (double) sum / arr.length;
            System.out.println("Среднее арифметическое: " + average);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за границы массива");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            System.out.println("Работа программы завершена");
        }
    }
}
