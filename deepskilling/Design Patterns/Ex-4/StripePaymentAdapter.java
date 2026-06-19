public class StripePaymentAdapter implements PaymentProcessor {
    private StripePaymentGateway stripeGateway;
    private String cardToken;
    
    public StripePaymentAdapter(String cardToken) {
        this.stripeGateway = new StripePaymentGateway();
        this.cardToken = cardToken;
    }
    
    @Override
    public void processPayment(double amount) {
        stripeGateway.chargeCard(cardToken, amount);
    }
}
