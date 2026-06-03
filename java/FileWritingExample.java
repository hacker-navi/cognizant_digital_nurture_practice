import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FileWritingExample {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string to write to output.txt: ");
            String input = scanner.nextLine();

            Files.writeString(
                    Path.of("output.txt"),
                    input + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            System.out.println("Data written to output.txt");
        }
    }
}
