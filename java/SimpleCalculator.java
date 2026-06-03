import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            double firstNumber = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double secondNumber = scanner.nextDouble();

            System.out.print("Choose operation (+, -, *, /): ");
            char operation = scanner.next().charAt(0);

            double result;
            switch (operation) {
                case '+' -> result = firstNumber + secondNumber;
                case '-' -> result = firstNumber - secondNumber;
                case '*' -> result = firstNumber * secondNumber;
                case '/' -> {
                    if (secondNumber == 0) {
                        System.out.println("Division by zero is not allowed.");
                        return;
                    }
                    result = firstNumber / secondNumber;
                }
                default -> {
                    System.out.println("Invalid operation.");
                    return;
                }
            }

            System.out.println("Result: " + result);
        }
    }
}
