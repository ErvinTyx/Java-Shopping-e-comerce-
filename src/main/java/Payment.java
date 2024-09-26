
public class Payment {

    private String paymentId;
    private double totalAmount;

    public Payment(double totalAmount) {
        this.totalAmount = totalAmount;
        this.paymentId = generatePaymentId();
    }
    public boolean processPayment(PaymentType paymentType) {
        boolean status = paymentType.processPayment(this.totalAmount);
        return status;
    }
    private String generatePaymentId() {
        return "PAY-" + Math.round(Math.random() * 100000);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void showPaymentDetails() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Total Amount: " + totalAmount);        
    }
}
