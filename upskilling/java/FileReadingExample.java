import java.nio.file.Files;
import java.nio.file.Path;

public class FileReadingExample {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("output.txt");

        if (!Files.exists(path)) {
            System.out.println("output.txt does not exist. Run FileWritingExample first.");
            return;
        }

        for (String line : Files.readAllLines(path)) {
            System.out.println(line);
        }
    }
}
