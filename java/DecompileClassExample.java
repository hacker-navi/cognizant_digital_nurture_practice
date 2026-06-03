public class DecompileClassExample {
    public static void main(String[] args) {
        System.out.println(new DecompileClassExample().buildMessage("decompiler"));
    }

    String buildMessage(String toolName) {
        return "Compile this class and inspect it with a decompiler: " + toolName;
    }
}
