/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author ervin
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Shop shop = new Shop();  // Initialize the shop
        UserManager userManager = new UserManager();  // Initialize user manager

        // Create some predefined users and add them to the user manager
        Admin admin = new Admin("Admin1", "adminpass");
        userManager.addUser(admin);
        // loop
        boolean loop = true;
        // Main menu
        while (loop) {
            clearScreen();
            System.out.println("\nWelcome to the Online Shopping Cart System");
            System.out.println("1. Log in");
            System.out.println("2. Sign up (Customer/Seller)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice :");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> login(userManager, shop, sc);
                case 2 -> signup(userManager, sc);
                case 3 -> {
                    System.out.println("Thank you for using the system.");
                    loop = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to handle user login
    private static void login(UserManager userManager, Shop shop, Scanner sc) {
        System.out.print("Enter username:");
        String username = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();

        UserBase user = userManager.findUser(username);

        if (user != null && user.validatePassword(password)) {
            user.accessControl(shop, sc);  // Call accessControl() for role-specific actions
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }



    // Method to handle user signup (Customer/Seller only)
    private static void signup(UserManager userManager, Scanner sc) {
        System.out.println("Sign up as:");
        System.out.println("1. Customer");
        System.out.println("2. Seller");

        int roleChoice = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        if (userManager.findUser(username) != null) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        UserBase newUser;
        switch (roleChoice) {
            case 1 -> {
                newUser = new Customer(username, password);
                System.out.println("Customer account created successfully!");
            }
            case 2 -> {
                newUser = new Seller(username, password);
                System.out.println("Seller account created successfully!");
            }
            default -> {
                System.out.println("Invalid role selection.");
                return;
            }
        }

        userManager.addUser(newUser);  // Add the new user to the system
    }
}
