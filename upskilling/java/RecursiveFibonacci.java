import java.util.Scanner;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a positive integer: ");
            int number = scanner.nextInt();

            System.out.println("Fibonacci(" + number + ") = " + fibonacci(number));
        }
    }

    static long fibonacci(int number) {
        if (number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
}
