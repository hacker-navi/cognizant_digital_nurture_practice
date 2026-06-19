import java.lang.System.Logger;

public class SingletonTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        logger1.log("First log entry");
        logger2.log("Second log entry");
        
        System.out.println("logger1 and logger2 are same instance: " + (logger1 == logger2));
    }
}
