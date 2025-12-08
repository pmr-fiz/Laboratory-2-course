import java.util.concurrent.atomic.AtomicInteger;

public class Stask {
    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = {
                {1, 5, 9},
                {4, 2, 7},
                {8, 3, 6}
        };

        int rows = matrix.length;
        AtomicInteger[] rowMax = new AtomicInteger[rows];
        for (int i = 0; i < rows; i++) {
            rowMax[i] = new AtomicInteger(Integer.MIN_VALUE);
        }

        Thread[] threads = new Thread[rows];

        for (int r = 0; r < rows; r++) {
            final int row = r;

            threads[r] = new Thread(() -> {
                int localMax = Integer.MIN_VALUE;

                for (int value : matrix[row]) {
                    if (value > localMax) {
                        localMax = value;
                    }
                }
                rowMax[row].set(localMax);
            });

            threads[r].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        int globalMax = Integer.MIN_VALUE;
        for (AtomicInteger max : rowMax) {
            if (max.get() > globalMax) {
                globalMax = max.get();
            }
        }

        System.out.println("Max element = " + globalMax);
    }
}
