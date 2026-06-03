import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class VirtualThreadsExample {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 100_000;
        CountDownLatch latch = new CountDownLatch(taskCount);
        Instant start = Instant.now();

        for (int index = 0; index < taskCount; index++) {
            Thread.startVirtualThread(() -> {
                if (latch.getCount() > taskCount - 5) {
                    System.out.println("Running on a virtual thread");
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("Completed " + taskCount + " virtual threads in " + Duration.between(start, Instant.now()).toMillis() + " ms");
    }
}
