import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCallableExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            List<Callable<String>> tasks = new ArrayList<>();
            tasks.add(() -> "Task 1 result");
            tasks.add(() -> "Task 2 result");
            tasks.add(() -> "Task 3 result");

            List<Future<String>> futures = executorService.invokeAll(tasks);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        } finally {
            executorService.shutdown();
        }
    }
}
