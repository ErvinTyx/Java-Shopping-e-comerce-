/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
import java.util.ArrayList;

public class Cart {

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    private ArrayList<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void viewCart() {
        System.out.println("Cart Contents:");
        //check cart is empty
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }else
        {
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    public void removeItem(int id) {
        //check cart have that item
        boolean found = items.removeIf(item -> item.getId() == id);
        if (!found) {
            System.out.println("Item ID not found in cart. Please try again.");
        }
    }


    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Proceeding to checkout...");
            items.clear();  // Clear the cart after checkout
        }
    }
    // get all total amount
    public double getTotalAmount() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
