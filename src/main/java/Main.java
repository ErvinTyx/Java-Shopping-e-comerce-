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
        userManager.addUser(admin);
        // loop
        boolean loop = true;
        int choice;
        // Main menu
        while (loop) {
            System.out.println("\nWelcome to the Online Shopping Cart System");
            System.out.println("1. Log in");
            System.out.println("2. Sign up (Customer/Seller)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice :");
            try {
                choice = sc.nextInt();
                sc.nextLine();  // Consume newline
                if (choice < 1 || choice > 3) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                sc.nextLine();  // Clear bad input
                continue;
            }

            switch (choice) {
                case 1 -> login(userManager, shop, sc);
                case 2 -> signup(userManager, sc);
                case 3 -> {
                    System.out.println("Thank you for using the system.");
                    loop = false;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    
                }
            }
        }
    }

    // Method to handle user login
    private static void login(UserManager userManager, Shop shop, Scanner sc) {
        int counter= 0;
        // loop for user enter more than 3 times
        do{
            
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
        }while(counter < 3);
    }




    // Method to handle user signup (Customer/Seller only)
    private static void signup(UserManager userManager, Scanner sc) {
        int roleChoice=0;
        boolean inputValid= false;
        String username;
        String password;
        do{
            
            
            do{
                System.out.println("Sign up as");
                System.out.println("1. Customer");
                System.out.println("2. Seller");
                System.out.println("3. Exit");
                System.out.print("Enter choice :");
                
                inputValid = sc.hasNextInt();
                if (inputValid) {
                    roleChoice = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    inputValid = true;
                    if (roleChoice < 1 || roleChoice > 4) {
                        inputValid = false;
                    }
                } else {
                    sc.nextLine();  // Clear invalid input
                    System.out.println("Invalid input. Please enter a number.");
                    inputValid = false;
                }
            } while (!inputValid);
            // exit function is user press 3 to exit 
            if(roleChoice == 3){
                break;
            }
            
            do{

                
                System.out.println("Enter username:");
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
                
            
            
            UserBase newUser;
            switch (roleChoice) {
                case 1 -> {
                    newUser = new Customer(username, password);
                    System.out.println("Customer account created successfully!");
                    userManager.addUser(newUser);
                }
                case 2 -> {
                    newUser = new Seller(username, password);
                    System.out.println("Seller account created successfully!");
                    userManager.addUser(newUser);
                }
                default -> {
                    System.out.println("Invalid role selection.");
                }
            }

        }while(false);

        
    }
}
