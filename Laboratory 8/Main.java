import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface DataProcessor {
    }

    public static class DataManager {

        private List<String> data = new ArrayList<>();
        private final List<Object> processors = new ArrayList<>();

        private final ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors()
                );

        public void registerDataProcessor(Object processor) {
            processors.add(processor);
        }

        public void loadData(String source) throws IOException {
            data = Files.readAllLines(Path.of(source));
        }

        public void processData() {
            for (Object processor : processors) {
                for (Method method : processor.getClass().getDeclaredMethods()) {
                    if (method.isAnnotationPresent(DataProcessor.class)) {
                        Future<List<String>> future =
                                executorService.submit(() -> {
                                    try {
                                        @SuppressWarnings("unchecked")
                                        List<String> result =
                                                (List<String>) method.invoke(processor, data);
                                        return result;
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });

                        try {
                            data = future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public void saveData(String destination) throws IOException {
            Files.write(Path.of(destination), data);
            executorService.shutdown();
        }

        public List<String> getData() {
            return data;
        }
    }

    public static class FilterProcessor {

        @DataProcessor
        public List<String> filter(List<String> data) {
            return data.stream()
                    .filter(s -> s.length() > 3)
                    .collect(Collectors.toList());
        }
    }

    public static class TransformProcessor {

        @DataProcessor
        public List<String> transform(List<String> data) {
            return data.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        }
    }

    public static class AggregateProcessor {

        @DataProcessor
        public List<String> aggregate(List<String> data) {
            return data.stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        try {
            DataManager manager = new DataManager();

            manager.registerDataProcessor(new FilterProcessor());
            manager.registerDataProcessor(new TransformProcessor());
            manager.registerDataProcessor(new AggregateProcessor());

            manager.loadData("input.txt");

            manager.processData();

            manager.saveData("output.txt");

            System.out.println("Результат:");
            manager.getData().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
