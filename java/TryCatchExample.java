import java.util.Scanner;

public class TryCatchExample {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first integer: ");
            int firstNumber = scanner.nextInt();

            System.out.print("Enter second integer: ");
            int secondNumber = scanner.nextInt();

            try {
                int result = firstNumber / secondNumber;
                System.out.println("Result: " + result);
            } catch (ArithmeticException exception) {
                System.out.println("Cannot divide by zero.");
            }
        }
    }
}
