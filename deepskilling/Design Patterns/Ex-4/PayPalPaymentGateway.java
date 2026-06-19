public class PayPalPaymentGateway {
    public void sendPayment(String email, double amount) {
        System.out.println("Sending payment to: " + email + " Amount: $" + amount + " via PayPal");
    }
}
