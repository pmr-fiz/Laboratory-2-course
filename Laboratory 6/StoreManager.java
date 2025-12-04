import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

public class StoreManager {

    private final ConcurrentHashMap<String, AtomicInteger> salesCount = new ConcurrentHashMap<>();
    private double totalAmount = 0;

    public void addSale(Product p) {
        salesCount.putIfAbsent(p.getName(), new AtomicInteger(0));
        salesCount.get(p.getName()).incrementAndGet();
        totalAmount += p.getPrice();
    }

    public void printSalesCount() {
        System.out.println("Количество проданных товаров:");
        for (Map.Entry<String, AtomicInteger> entry : salesCount.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().get() + " шт.");
        }
    }

    public double getTotalSalesAmount() {
        return totalAmount;
    }

    public String getMostPopularProduct() {
        String best = null;
        int max = 0;

        for (Map.Entry<String, AtomicInteger> entry : salesCount.entrySet()) {
            int count = entry.getValue().get();
            if (count > max) {
                max = count;
                best = entry.getKey();
            }
        }
        return best;
    }

    public static void main(String[] args) {
        StoreManager manager = new StoreManager();
        manager.addSale(new Product("Хлеб", 40));
        manager.addSale(new Product("Молоко", 70));
        manager.addSale(new Product("Хлеб", 40));
        manager.addSale(new Product("Сок", 120));
        manager.addSale(new Product("Хлеб", 40));
        manager.printSalesCount();
        System.out.println("\nОбщая сумма продаж: " + manager.getTotalSalesAmount() + " руб.");
        System.out.println("Самый популярный товар: " + manager.getMostPopularProduct());
    }
}


