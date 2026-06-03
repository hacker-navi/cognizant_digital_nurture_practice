import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            int secretNumber = random.nextInt(100) + 1;
            int guess;

            do {
                System.out.print("Guess a number between 1 and 100: ");
                guess = scanner.nextInt();

                if (guess < secretNumber) {
                    System.out.println("Too low.");
                } else if (guess > secretNumber) {
                    System.out.println("Too high.");
                }
            } while (guess != secretNumber);

            System.out.println("Correct! The number was " + secretNumber + ".");
        }
    }
}
