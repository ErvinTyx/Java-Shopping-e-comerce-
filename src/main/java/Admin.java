
import java.util.InputMismatchException;
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
        boolean loop = true;
        while (loop) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View All Users");
            System.out.println("2. Close shop(terminate program)");
            System.out.println("3. Add New Admin");
            System.out.println("4. Log out");
            System.out.print("Enter your choice: ");
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
                    shop.listUsers();
                    break;
                case 2:
                    shop.openCloseShop();
                    break;
                case 3:
                    addNewAdmin(sc, shop);
                    break;
                case 4:
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Admin-only method to add a new admin
    private void addNewAdmin(Scanner sc, Shop shop) {
        boolean inputValid = false;
        String username;
        String password;
        do {

            System.out.print("Enter new admin username:");
            username = sc.nextLine();
            if (shop.getUserManager().findUser(username) != null) {
                System.out.println("Username already exists. Please choose a different username.");
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
            inputValid =shop.getUserManager().isValidPassword(password);

        } while (!inputValid);

        Admin newAdmin = new Admin(username, password);
        shop.getUserManager().addUser(newAdmin);
        System.out.println("New admin added successfully.");
    }
}
