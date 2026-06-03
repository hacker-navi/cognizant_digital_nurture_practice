public class ThreadCreationExample {
    public static void main(String[] args) throws InterruptedException {
        Runnable taskOne = () -> {
            for (int count = 1; count <= 5; count++) {
                System.out.println("Task 1 - message " + count);
            }
        };

        Runnable taskTwo = () -> {
            for (int count = 1; count <= 5; count++) {
                System.out.println("Task 2 - message " + count);
            }
        };

        Thread firstThread = new Thread(taskOne);
        Thread secondThread = new Thread(taskTwo);

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();
    }
}
