import java.util.concurrent.atomic.AtomicInteger;

public class Ftask {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        int mid = arr.length / 2;

        AtomicInteger sum1 = new AtomicInteger(0);
        AtomicInteger sum2 = new AtomicInteger(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                sum1.addAndGet(arr[i]);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = mid; i < arr.length; i++) {
                sum2.addAndGet(arr[i]);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int total = sum1.get() + sum2.get();
        System.out.println("Sum = " + total);
    }
}
