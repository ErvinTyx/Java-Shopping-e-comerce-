
import java.util.Scanner;
import java.util.InputMismatchException;

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

    private void addNewSeller(Scanner sc, Shop shop) {

        boolean inputValid = false;
        String username;
        String password;
        do {

            System.out.print("Enter new seller username:");
            username = sc.nextLine();
            if (shop.getUserManager().findUser(username) != null) {
                System.out.println("Username already exists. Please choose a different username.");
                inputValid = false;
            } else {
                inputValid = true;
            }
        } while (!inputValid);

        do {
            if (inputValid) {

                System.out.println("Please make sure password 8 or more characters long.");
                System.out.println("Please make sure password has at least 1 uppercase, 1 lowercase, 1 digit and 1 special character");
            }
            inputValid = false;
            System.out.print("Enter password:");
            password = sc.nextLine();
            inputValid = shop.getUserManager().isValidPassword(password);
        } while (!inputValid);

        Seller newSeller = new Seller(username, password);
        shop.getUserManager().addUser(newSeller);
        System.out.println("New seller added successfully.");
    }

    @Override
    public void accessControl(Shop shop, Scanner sc) {
        boolean loop = false;
        while (!loop) {
            System.out.println("\nSeller Menu:");
            System.out.println("1. View Items for Sale");
            System.out.println("2. Add New Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Add new Seller");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice:");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    shop.listItems();
                    break;
                case 2:
                    System.out.print("Enter new item details name :");
                    String name = sc.nextLine();
                    System.out.print("Enter item price :");
                    double price;
                    try {
                        price = sc.nextDouble();
                        if (price <= 0.9) {
                            System.out.println("Invalid Price. Please enter a valid price which is more than zero. Please try again.");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid price. Please try again.");
                        sc.nextLine();
                        continue;
                    }
                    shop.addItem(new Item(name, price));
                    System.out.println("Item added: " + name);
                    break;
                case 3:
                    if(shop.getItems().isEmpty()){
                        System.out.println("There are no items to remove. Add an item first.");
                    }else{
                        shop.listItems();
                        System.out.print("Enter item ID to remove:");
                        int itemId;
                        try {
                            itemId = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid item ID.");
                            sc.nextLine();
                            continue;
                        }
                        shop.removeItem(itemId);
                        
                    }
                    break;
                case 4:
                    addNewSeller(sc, shop);
                    break;
                case 5:
                    loop = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
