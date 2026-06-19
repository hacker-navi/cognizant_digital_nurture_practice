public class PayPalPaymentAdapter implements PaymentProcessor {
    private PayPalPaymentGateway paypalGateway;
    private String email;
    
    public PayPalPaymentAdapter(String email) {
        this.paypalGateway = new PayPalPaymentGateway();
        this.email = email;
    }
    
    @Override
    public void processPayment(double amount) {
        paypalGateway.sendPayment(email, amount);
    }
}
