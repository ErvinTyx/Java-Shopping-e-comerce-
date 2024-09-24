
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
        this.items = new ArrayList<>(items);
        this.quantities = new ArrayList<>(quantities);
        this.status = OrderStatus.PLACED; // default status when the order is created
        rewritequantity();
    }

    private void rewritequantity() {
        // rewrite quantity to item
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setQuantity(quantities.get(i));
        }
    }

    // Getters
    public int getOrderId() {
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
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Order Status: " + status);
        System.out.println("Items Ordered: ");
        // display items ordered and quantities
        System.out.printf("%-8s|%-20s|%-15s|%13s|%13s|\n", "Item ID", "Name", "Price(Per Item)", "Quantity","Total Price Item");
        double grandtotal = 0;
        
        if (items.size() != quantities.size()) {
            System.out.println("Error: Items and quantities lists must be of the same size.");
            return; // Exit the method to prevent further errors
        }
        
        // Display each item in a properly formatted row
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i); // Get the item
            int quantity = quantities.get(i); // Get the corresponding quantity
            double totalPriceItem = quantity * item.getPrice(); // Calculate total price for the item

            // Display each item in a properly formatted row
            System.out.printf("%-8s|%-20s|$%-14.2f|%13d|$%15.2f|\n",
                    item.getId(),     // Item ID
                    item.getName(),   // Item Name
                    item.getPrice(),  // Price per Item
                    quantity,         // Quantity
                    totalPriceItem    // Total Price per Item
            );

            // Accumulate total price for the order
            grandtotal += totalPriceItem;
        }
    
        // Display grand total
        System.out.println("Grand Total: $" + String.format("%.2f", grandtotal));
    }
    
    public enum OrderStatus {
        PLACED,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }
}
