public class FinancialForecasting {

    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double presentValue = 10000.0;
        double annualGrowthRate = 0.08;
        int years = 5;

        double futureValue = calculateFutureValue(presentValue, annualGrowthRate, years);

        System.out.println("Present Value  : $" + presentValue);
        System.out.println("Growth Rate    : " + (annualGrowthRate * 100) + "%");
        System.out.println("Years          : " + years);
        System.out.printf("Future Value   : $%.2f%n", futureValue);

        System.out.println("\n--- Analysis ---");
        System.out.println("Time Complexity: O(n) where n is the number of years.");
        System.out.println("Each recursive call reduces the year count by 1 until it hits the base case.");
        System.out.println("To optimize, use memoization or simply convert to an iterative loop.");
        System.out.println("Iterative approach avoids stack overflow for very large year values.");
    }
}
