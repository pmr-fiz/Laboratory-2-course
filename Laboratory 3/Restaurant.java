import java.time.LocalTime;
import java.util.HashMap;

class Order {
    private String dishes;
    private double totalPrice;
    private LocalTime orderTime;

    public Order(String dishes, double totalPrice, int hour, int min) {
        this.dishes = dishes;
        this.totalPrice = totalPrice;
        this.orderTime = LocalTime.of(hour, min);
    }

    public String getDishes() {
        return dishes;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    @Override
    public String toString() {
        return "Блюда: " + dishes +
               ", Стоимость: " + totalPrice +
               ", Время: " + orderTime;
    }
}

public class Restaurant {
    private HashMap<Integer, Order> orders = new HashMap<>();

    public void addOrder(int tableNumber, Order order) {
        orders.put(tableNumber, order);
        System.out.println("Заказ добавлен для столика №" + tableNumber);
    }

    public Order getOrder(int tableNumber) {
        Order order = orders.get(tableNumber);
        if (order == null) {
            System.out.println("Заказ для столика №" + tableNumber + " не найден.");
        }
        return order;
    }

    public void removeOrder(int tableNumber) {
        if (orders.remove(tableNumber) != null) {
            System.out.println("Заказ для столика №" + tableNumber + " удалён.");
        } else {
            System.out.println("Не найден заказ для удаления (столик №" + tableNumber + ").");
        }
    }

    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return "Нет активных заказов.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Все заказы:\n");

        int count = 0;
        int size = orders.size();

        for (var entry : orders.entrySet()) {
            sb.append("Столик №").append(entry.getKey())
            .append(" ").append(entry.getValue());
            count++;
            if (count < size) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        Order order1 = new Order("Паста Болоньезе, Капучино", 850.0, 9, 30);
        Order order2 = new Order("Стейк, Салат Цезарь, Сок", 1500.0, 10, 20);
        Order order3 = new Order("Суп, Компот", 400.0, 10, 30);

        restaurant.addOrder(1, order1);
        restaurant.addOrder(2, order2);
        restaurant.addOrder(3, order3);

        System.out.println(restaurant);

        System.out.println("Поиск заказа для столика №2:");
        System.out.println(restaurant.getOrder(2));

        restaurant.removeOrder(1);
        System.out.println(restaurant);
    }
}

