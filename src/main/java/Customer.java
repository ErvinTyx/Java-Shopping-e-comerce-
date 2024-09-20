
import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
public class Customer extends UserBase {

    private Cart cart;

    public Customer(String username, String password) {
        super(username, password, "Customer");
        this.cart = new Cart();
    }

    @Override
    public void accessControl(Shop shop, Scanner sc) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Log out");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    shop.listItems();
                    break;
                case 2:
                    System.out.print("Enter item ID to add to cart:");
                    int itemId = sc.nextInt();
                    Item item = shop.getItemById(itemId);
                    if (item != null) {
                        cart.addItem(item);
                        System.out.println("Item added to cart: " + item.getName());
                    } else {
                        System.out.println("Invalid item ID.");
                    }
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    cart.checkout();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public Cart getCart() {
        return cart;
    }
}

