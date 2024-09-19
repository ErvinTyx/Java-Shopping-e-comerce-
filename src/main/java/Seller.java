

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */


public class Seller extends UserBase {

    public Seller(String username, String password) {
        super(username, password, "Seller");
    }

    @Override
    public void accessControl(Shop shop, Scanner sc) {
        while (true) {
            System.out.println("\nSeller Menu:");
            System.out.println("1. View Items for Sale");
            System.out.println("2. Add New Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    shop.listItems();
                    break;
                case 2:
                    System.out.println("Enter new item details (name, price):");
                    String name = sc.nextLine();
                    double price = sc.nextDouble();
                    shop.addItem(new Item(name, price));
                    System.out.println("Item added: " + name);
                    break;
                case 3:
                    System.out.println("Enter item ID to remove:");
                    int itemId = sc.nextInt();
                    shop.removeItem(itemId);
                    System.out.println("Item removed.");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}

