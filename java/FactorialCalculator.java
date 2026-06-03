import java.math.BigInteger;
import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a non-negative integer: ");
            int number = scanner.nextInt();

            if (number < 0) {
                System.out.println("Factorial is not defined for negative numbers.");
                return;
            }

            BigInteger factorial = BigInteger.ONE;
            for (int factor = 2; factor <= number; factor++) {
                factorial = factorial.multiply(BigInteger.valueOf(factor));
            }

            System.out.println("Factorial: " + factorial);
        }
    }
}
