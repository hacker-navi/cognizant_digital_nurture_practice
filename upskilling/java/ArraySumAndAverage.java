import java.util.Scanner;

public class ArraySumAndAverage {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of elements: ");
            int size = scanner.nextInt();
            int[] numbers = new int[size];
            int sum = 0;

            for (int index = 0; index < size; index++) {
                System.out.print("Enter element " + (index + 1) + ": ");
                numbers[index] = scanner.nextInt();
                sum += numbers[index];
            }

            double average = size == 0 ? 0 : (double) sum / size;
            System.out.println("Sum: " + sum);
            System.out.println("Average: " + average);
        }
    }
}
