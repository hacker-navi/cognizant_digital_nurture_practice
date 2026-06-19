import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Class<?> type = Class.forName(ReflectionExample.ReflectionTarget.class.getName());

        for (Method method : type.getDeclaredMethods()) {
            System.out.println(method.getName() + Arrays.toString(method.getParameterTypes()));
        }

        Object instance = type.getDeclaredConstructor().newInstance();
        Method greetMethod = type.getDeclaredMethod("greet", String.class);
        System.out.println(greetMethod.invoke(instance, "Java"));
    }

    static class ReflectionTarget {
        public String greet(String name) {
            return "Hello, " + name;
        }
    }
}
