
public abstract class PaymentType {

    protected String paymentMethod;

    public PaymentType(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public abstract boolean processPayment(double totalAmount);
}
