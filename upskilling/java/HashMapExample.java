import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, String> studentMap = new HashMap<>();

            System.out.print("How many entries do you want to add? ");
            int count = scanner.nextInt();

            for (int index = 0; index < count; index++) {
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                studentMap.put(id, name);
            }

            System.out.print("Enter an ID to retrieve: ");
            int lookupId = scanner.nextInt();
            System.out.println("Name: " + studentMap.getOrDefault(lookupId, "Not found"));
        }
    }
}
