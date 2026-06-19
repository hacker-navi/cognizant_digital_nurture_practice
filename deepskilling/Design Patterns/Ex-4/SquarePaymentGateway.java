public class SquarePaymentGateway {
    public void executeTransaction(String transactionId, double amount) {
        System.out.println("Executing transaction ID: " + transactionId + " Amount: $" + amount + " via Square");
    }
}
