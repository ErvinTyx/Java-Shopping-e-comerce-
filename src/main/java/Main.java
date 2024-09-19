/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author ervin
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Shop shop = new Shop();  // Initialize the shop
        UserManager userManager = new UserManager();  // Initialize user manager

        // Create some predefined users and add them to the user manager
        Admin admin = new Admin("Admin1", "adminpass");
        Seller seller = new Seller("Seller1", "sellerpass");
        Customer user = new Customer("User1", "userpass");
        userManager.addUser(user);
        userManager.addUser(seller);
        userManager.addUser(admin);
        // loop
        boolean loop = true;
        int choice;
        // Main menu
        while (loop) {
            System.out.println("\nWelcome to the Online Shopping Cart System");
            System.out.println("1. Log in");
            System.out.println("2. Sign up as a new user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice :");
            try {
                choice = sc.nextInt();
                sc.nextLine();  // Consume newline
                if (choice < 1 || choice > 2) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a number between 1 and 2.");
                sc.nextLine();  // Clear bad input
                continue;
            }

            switch (choice) {
                case 1 -> login(userManager, shop, sc);
                case 2 -> signup(userManager, sc);

                default -> {
                    System.out.println("Invalid option. Please try again.");
                    
                }
            }
            if(shop.isShopopen()){
                loop = false;
            }
        }
    }

    // Method to handle user login
    private static void login(UserManager userManager, Shop shop, Scanner sc) {
        int counter= 0;
        boolean loop = true;
        // loop for user enter more than 3 times
        do{
            
            System.out.print("Enter username:");
            String username = sc.nextLine();
            System.out.print("Enter password:");
            String password = sc.nextLine();
            
            UserBase user = userManager.findUser(username);
            
            if (user != null && user.validatePassword(password)) {
                user.accessControl(shop, sc);  // Call accessControl() for role-specific actions
                loop = false;
            } else {
                System.out.println("Invalid credentials.");
                counter++;
            }
        }while(counter < 3 && loop);
    }




    // Method to handle user signup (Customer/Seller only)
    private static void signup(UserManager userManager, Scanner sc) {

        boolean inputValid= false;
        String username;
        String password;
        

            
            do{
                System.out.println("Enter your new username:");
                username = sc.nextLine();
                if (userManager.findUser(username) != null) {
                    System.out.println("Username already exists. Please choose a different username.");
                    inputValid = false;
                }
                else{
                    inputValid = true;
                }
            }while(!inputValid);

            do{
                if(!inputValid){

                    System.out.println("Please make sure password 8 or more characters long.");
                    System.out.println("Please make sure password has at least 1 uppercase, 1 lowercase, 1 digit and 1 special character");
                }
                    System.out.println("Enter password:");
                password = sc.nextLine();
                if(!userManager.isValidPassword(password)){
                    inputValid = false;
                }
                else{
                    inputValid = true;
                }

            }while(inputValid);
                
            
            
            UserBase newUser = new Customer(username, password);



            userManager.addUser(newUser);

        
    }
}
