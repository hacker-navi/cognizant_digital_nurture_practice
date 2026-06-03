import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int number = scanner.nextInt();

            for (int factor = 1; factor <= 10; factor++) {
                System.out.println(number + " x " + factor + " = " + (number * factor));
            }
        }
    }
}
