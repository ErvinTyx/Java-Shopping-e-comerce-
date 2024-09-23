public class OrderStatus {
    public enum Status {
        PLACED,
        PROCESSING,
        SHIPPED,
        DELIVERED,
    }
    
    private Status status;

    public OrderStatus(Status initialStatus) {
        this.status = initialStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void updateOrderStatus(Status newStatus) {
        this.status = newStatus;
    }
}
