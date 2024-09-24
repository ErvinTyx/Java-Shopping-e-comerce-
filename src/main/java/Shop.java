import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> items;
    private UserManager userManager;
    private OrderManager orderManager;
    private boolean shopopen;
    private ArrayList<Order> orderHistory = new ArrayList<>();


    public Shop(UserManager userManager) {
        shopopen = true;
        this.userManager = userManager;
        orderManager = new OrderManager();
        items = new ArrayList<>();
        this.orderHistory = new ArrayList <>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == (id)) { 
                return item;
            }
        }
        return null;
    }

    public void removeItem(int id) {
        boolean found = items.removeIf(item -> item.getId() == id);
        if (found) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item ID does not exist. Enter an existing ID.");
        }
    }

    public void listItems() {
        if (items == null || items.isEmpty()) {
            System.out.println("No items available for sale.");
        } else {
            System.out.println("Items for Sale:");
            System.out.printf("%-8s|%-20s|%-15s|%10s|","Item Id","Item name", "Item Price","Quantity left");
            System.out.println("");
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }


    public void updateQuantity(int id, int newQuantity) {
        Item item = getItemById(id);
        if (item != null) {
            item.setQuantity(newQuantity);
            System.out.println("Quantity of " + item.getName() + " updated to " + newQuantity + ".");
        } else {
            System.out.println("Item ID does not exist. Please enter an existing ID.");
        }
    }

    public void openCloseShop() {
        shopopen = !shopopen;
        if (shopopen) {
            System.out.println("Shop is now open.");
        } else {
            System.out.println("Shop is now closed. Please log out to terminate the program.");
        }
    }

    public void listUsers() {
        System.out.println("Registered Users:");
        for (UserBase user : userManager.getUsers()) {
            System.out.println("Username: " + user.getUsername() + "\nRole: (" + user.getRole() + ")\n");
        }
    }
    
    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }
    
    public void viewOrderHistory(String customerName) {
        System.out.println("Order History for " + customerName + ":");
        boolean hasOrders = false; // Flag to check if the customer has orders
        for (Order order : orderHistory) {
            if (order.getCustomerName().equals(customerName)) {
                order.displayOrder(); // Display each order for the customer
                hasOrders = true; // Set flag to true if there are orders
            }
        }
        if (!hasOrders) {
            System.out.println("No orders found for this customer."); // Message if no orders exist
        }
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public OrderManager getOrderManager(){
        return orderManager;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean isShopopen() {
        return shopopen;
    }

    public void setShopopen(boolean shopopen) {
        this.shopopen = shopopen;
    }
}
