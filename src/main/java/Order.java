
import java.util.ArrayList;

public class Order {
    private static int counter =1;
    private String customerName;
    private ArrayList<Item> items;
    private OrderStatus status;
    private ArrayList<Integer> quantities; 
    private int orderId;

    // Constructor
    public Order(String customerName, ArrayList<Item> items, ArrayList<Integer> quantities) {
        this.orderId = counter;
        counter++;
        this.customerName = customerName;
        this.items = items;
        this.quantities = quantities;
        this.status = OrderStatus.PLACED; // default status when the order is created
        rewritequantity();
    }

    private void rewritequantity() {
        // rewrite quantity to item
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.setQuantity(quantities.get(i));
        }
        
    }


    // Getters
    public int getOrderId() {
        return counter;
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
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Order Status: " + status);
        System.out.println("Items Ordered: ");
        // display items ordered and quantities
        System.out.printf("%-8s|%-20s|%-13s|%13s|%13s|\n", "Item ID", "Name", "Price(Per Item)", "Quantity","Total Price Item");
        double grandtotal = 0;
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            double subtotal = item.getQuantity() * item.getPrice();
            System.out.printf("%-8d|%-20s|%-13.2f|%13d|$%13.2f|\n", item.getId(), item.getName(), item.getPrice(), item.getQuantity(), subtotal);
            grandtotal += subtotal;
        }
        System.out.println("Grand Total: $" + grandtotal);
    }    
    public enum OrderStatus {
        PLACED,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }
}
