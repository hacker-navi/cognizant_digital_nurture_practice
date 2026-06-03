public class TypeCastingExample {
    public static void main(String[] args) {
        double doubleValue = 12.75;
        int intValue = (int) doubleValue;

        int originalInt = 25;
        double widenedDouble = (double) originalInt;

        System.out.println("Double to int: " + intValue);
        System.out.println("Int to double: " + widenedDouble);
    }
}
