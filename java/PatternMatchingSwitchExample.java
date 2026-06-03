public class PatternMatchingSwitchExample {
    public static void main(String[] args) {
        describeValue(42);
        describeValue("Java 21");
        describeValue(3.14);
        describeValue(true);
        describeValue(null);
    }

    static void describeValue(Object value) {
        String message = switch (value) {
            case null -> "Value is null";
            case Integer integerValue -> "Integer: " + integerValue;
            case String text -> "String: " + text;
            case Double doubleValue -> "Double: " + doubleValue;
            case Boolean booleanValue -> "Boolean: " + booleanValue;
            default -> "Other type: " + value.getClass().getSimpleName();
        };

        System.out.println(message);
    }
}
