public class MethodOverloadingDemo {
    public static void main(String[] args) {
        System.out.println(add(3, 7));
        System.out.println(add(2.5, 4.5));
        System.out.println(add(1, 2, 3));
    }

    static int add(int first, int second) {
        return first + second;
    }

    static double add(double first, double second) {
        return first + second;
    }

    static int add(int first, int second, int third) {
        return first + second + third;
    }
}
