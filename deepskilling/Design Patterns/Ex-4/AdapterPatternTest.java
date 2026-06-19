public class AdapterPatternTest {
    public static void main(String[] args) {
        PaymentProcessor stripePayment = new StripePaymentAdapter("token_xyz123");
        stripePayment.processPayment(99.99);
        
        System.out.println();
        
        PaymentProcessor paypalPayment = new PayPalPaymentAdapter("user@example.com");
        paypalPayment.processPayment(49.99);
        
        System.out.println();
        
        PaymentProcessor squarePayment = new SquarePaymentAdapter("txn_12345");
        squarePayment.processPayment(75.50);
    }
}
