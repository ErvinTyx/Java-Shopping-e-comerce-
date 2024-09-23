import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Listing {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Item> items;

    public Listing() {
        // Creating items with quantities
        items = new ArrayList<>(Arrays.asList(
            new Item("Laptop", 4000.00, 10),
            new Item("Smartphone", 5900.00, 20),
            new Item("Bluetooth Speaker", 340.00, 50),
            new Item("Eyeliner", 89.00, 100),
            new Item("Liquid Foundation", 300.00, 80),
            new Item("Lipstick", 200.00, 150),
            new Item("Air Fryer", 350.00, 25),
            new Item("Coffee Maker", 699.00, 30),
            new Item("Sunglasses", 120.00, 40),
            new Item("Earrings", 30.00, 200)
        ));
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }

    public void initializeShop(Shop shop) {
        for (Item item : items) {
            shop.addItem(item);
        }

        // Creating an order and adding it to the OrderManager
        Order order1 = new Order("ORD123", "John Doe", (ArrayList<Item>) Arrays.asList(items.get(0), items.get(1)));
        shop.getOrderManager().addOrder(order1);

        // Creating a seller
        Seller seller = new Seller("BestSeller", "password"); // Assuming password is required
        

        // Seller viewing order history
        seller.viewOrderHistory(shop);

        // Updating order status
        seller.updateOrderStatus(sc, shop);

        // Viewing the updated order history
        seller.viewOrderHistory(shop);
    }
}
