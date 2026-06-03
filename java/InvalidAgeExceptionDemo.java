import java.util.Scanner;

public class InvalidAgeExceptionDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            try {
                validateAge(age);
                System.out.println("Age accepted.");
            } catch (InvalidAgeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18.");
        }
    }

    static class InvalidAgeException extends Exception {
        InvalidAgeException(String message) {
            super(message);
        }
    }
}
