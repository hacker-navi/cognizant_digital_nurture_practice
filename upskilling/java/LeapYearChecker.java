import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a year: ");
            int year = scanner.nextInt();

            boolean leapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
            System.out.println(year + (leapYear ? " is a leap year." : " is not a leap year."));
        }
    }
}
