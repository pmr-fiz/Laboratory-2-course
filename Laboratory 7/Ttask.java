import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Ttask {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> goods = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
            50, 30, 20, 60, 40, 25, 35, 15, 10, 90
        )));

        int maxWeight = 150;
        int numWorkers = 3;

        AtomicInteger totalWeight = new AtomicInteger(0);

        CyclicBarrier barrier = new CyclicBarrier(numWorkers, () -> {
            System.out.println("Грузчики достигли лимита. Отправляем товары на другой склад!\n");
            totalWeight.set(0);
        });

        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);
        AtomicBoolean ask = new AtomicBoolean(false);

        for (int i = 1; i <= numWorkers; i++) {
            int id = i;
            executor.execute(() -> {
                while (true) {
                    boolean tookItem = false;

                    synchronized (goods) {
                        Iterator<Integer> iterator = goods.iterator();
                        while (iterator.hasNext()) {
                            int item = iterator.next();
                            if (totalWeight.get() + item <= maxWeight) {
                                iterator.remove();
                                int currentTotal = totalWeight.addAndGet(item);
                                System.out.println("Грузчик " + id + " взял товар " + item +
                                        " кг. Суммарно по всем грузчикам: " + currentTotal + " кг");
                                tookItem = true;
                                break;
                            }
                        }
                    }

                    if (!tookItem) {
                        synchronized (goods) {
                            if (goods.isEmpty()) {
                                if (ask.get() == false && totalWeight.get() != 0) {
                                    System.out.println("Грузчики достигли лимита. Отправляем товары на другой склад!");
                                }
                                barrier.reset();
                                ask.compareAndSet(false, true);
                                break;
                            }
                        }
                    }

                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        break;
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Все товары перенесены!");
    }
}
