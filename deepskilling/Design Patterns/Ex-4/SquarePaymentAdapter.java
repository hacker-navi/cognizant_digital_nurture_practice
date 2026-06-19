public class SquarePaymentAdapter implements PaymentProcessor {
    private SquarePaymentGateway squareGateway;
    private String transactionId;
    
    public SquarePaymentAdapter(String transactionId) {
        this.squareGateway = new SquarePaymentGateway();
        this.transactionId = transactionId;
    }
    
    @Override
    public void processPayment(double amount) {
        squareGateway.executeTransaction(transactionId, amount);
    }
}
