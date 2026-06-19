public class StripePaymentGateway {
    public void chargeCard(String cardToken, double amount) {
        System.out.println("Charging card with token: " + cardToken + " Amount: $" + amount + " via Stripe");
    }
}
