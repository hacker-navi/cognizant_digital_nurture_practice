import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> studentNames = new ArrayList<>();

            System.out.print("How many names do you want to add? ");
            int count = scanner.nextInt();
            scanner.nextLine();

            for (int index = 0; index < count; index++) {
                System.out.print("Enter name " + (index + 1) + ": ");
                studentNames.add(scanner.nextLine());
            }

            System.out.println("Names entered:");
            for (String name : studentNames) {
                System.out.println(name);
            }
        }
    }
}
