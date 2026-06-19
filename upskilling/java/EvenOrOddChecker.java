import java.util.Scanner;

public class EvenOrOddChecker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter an integer: ");
            int number = scanner.nextInt();

            System.out.println(number % 2 == 0 ? "Even" : "Odd");
        }
    }
}
