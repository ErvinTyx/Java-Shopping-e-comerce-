import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;
    private ArrayList<Integer> quantities;  // Track quantities for each item in the cart
    private String customerName;
    
    public Cart(String customerName) {
        this.customerName = customerName;
        items = new ArrayList<>();
        quantities = new ArrayList<>(); 
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }
    // Add item to cart and track its quantity
    public void addItem(Item item, int quantity) {
        Item itemInCart = new Item(item.getName(), item.getPrice(), item.getQuantity());
        boolean itemExistsInCart = false;

        // Check if item is already in the cart
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == itemInCart.getId()){
                // Update the quantity if item already exists in cart
                quantities.set(i, quantities.get(i) + quantity);
                itemExistsInCart = true;
                break;
            }
        }

        if (!itemExistsInCart) {
            // Add new item to cart if it doesn't already exist
            items.add(itemInCart);
            quantities.add(quantity);
        }

        // Decrease the available stock from the shop
        item.setQuantity(item.getQuantity() - quantity);
    }

    // Remove item from cart
    public void removeItem(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == itemId){
                // Increase the stock in the shop when removing from the cart
                Item item = items.get(i);
                item.setQuantity(item.getQuantity() + quantities.get(i)); // Restore stock
                items.remove(i);
                quantities.remove(i);
                System.out.println("Item removed from cart successfully.");
                return;
            }
        }
        System.out.println("Item not found in cart.");
    }

    // Display the contents of the cart in a formatted table and include the total amount
    public void viewCart() {
        System.out.println("Cart Contents:");
    
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.printf("%-10s|%-20s|%-10s|%-10s|\n", "Item ID", "Name", "Price", "Quantity");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.printf("%-10d|%-20s|%-10.2f|%-10d|\n", item.getId(), item.getName(), item.getPrice(), quantities.get(i));
            }
            // Print the total amount at the end
            System.out.printf("%nTotal Amount: $%.2f\n", getTotalAmount());
        }
    }

    // Calculate the total amount of the cart based on item prices and quantities
    public double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice() * quantities.get(i);
        }
        return total;
    }
    
    public void checkout(Shop shop) {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Proceeding to checkout...");
            
            ArrayList<Integer> quantities = new ArrayList<>(this.quantities);
            Order newOrder = new Order(customerName, new ArrayList<>(items), quantities);
            shop.addOrderToHistory(newOrder); 

            finalizeCheckout(shop);
        }
    }

    // Update stock after successful payment
    public void finalizeCheckout(Shop shop) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int quantityInCart = quantities.get(i);
            item.setQuantity(item.getQuantity() - quantityInCart); // Deduct stock
        }
        items.clear();
        quantities.clear();
        System.out.println("Checkout completed successfully.");
    }
}
