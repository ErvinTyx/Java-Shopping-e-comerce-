
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
public class Admin extends UserBase {

    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    @Override
    public void accessControl(Shop shop, Scanner sc) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View All Users");
            System.out.println("2. Manage Categories");
            System.out.println("3. Add New Admin");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    shop.listUsers();
                    break;
                case 2:
                    System.out.println("Enter category name to add:");
                    String categoryName = sc.nextLine();
                    shop.addCategory(new Category(categoryName));
                    System.out.println("Category added: " + categoryName);
                    break;
                case 3:
                    addNewAdmin(sc, shop);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Admin-only method to add a new admin
    private void addNewAdmin(Scanner sc, Shop shop) {
        System.out.println("Enter new admin username:");
        String username = sc.nextLine();
        System.out.println("Enter new admin password:");
        String password = sc.nextLine();

        // Check if user already exists
        if (shop.getUserManager().findUser(username) != null) {
            System.out.println("Username already exists. Cannot create admin.");
            return;
        }

        Admin newAdmin = new Admin(username, password);
        shop.getUserManager().addUser(newAdmin);
        System.out.println("New admin added successfully.");
    }
}