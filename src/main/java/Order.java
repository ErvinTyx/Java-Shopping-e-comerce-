
import java.util.ArrayList;

public class Order {
    private String orderId;
    private String customerName;
    private ArrayList<Item> items;
    private OrderStatus status;

    // Constructor
    public Order(String orderId, String customerName, ArrayList<Item> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.status = OrderStatus.PLACED; // default status when the order is created
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // Method to update the status of the order
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    // Display order details
    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Order Status: " + status);
        System.out.println("Items Ordered: ");
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }
    
    public enum OrderStatus {
        PLACED,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }
}
