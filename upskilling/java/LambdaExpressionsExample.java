import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaExpressionsExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("banana", "apple", "orange", "grape");

        Collections.sort(words, (first, second) -> first.compareTo(second));

        System.out.println(words);
    }
}
