
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();  // Initialize user manager
        Shop shop = new Shop(userManager);  // Initialize the shop

        // Create some predefined users and add them to the user manager
        UserBase admin = new Admin("admin1", "adminpass");
        UserBase seller = new Seller("seller1", "sellerpass");
        UserBase customer = new Customer("user1", "userpass");
        userManager.addUser(admin);
        userManager.addUser(seller);
        userManager.addUser(customer);

        // Main menu loop
        boolean loop = true;
        while (loop) {
            System.out.println("\nWelcome to the Online Shopping Cart System");
            System.out.println("1. Log in");
            System.out.println("2. Sign up as a new user");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();  // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 ->
                    login(userManager, shop, sc);
                case 2 ->
                    signup(userManager, sc);
                default ->
                    System.out.println("Invalid option. Please try again.");
            }

            if (!shop.isShopopen()) {
                loop = false;
            }
        }
    }

    private static void login(UserManager userManager, Shop shop, Scanner sc) {
        int counter = 0;
        boolean loop = true;
        do {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            UserBase user = userManager.findUser(username);

            if (user != null && user.validatePassword(password)) {
                user.accessControl(shop, sc);  // Call accessControl() for role-specific actions
                loop = false;
            } else {
                System.out.println("Invalid credentials.");
                counter++;
                System.out.println("Try again. You have " + (3 - counter) + " attempts left.");
            }
        } while (counter < 3 && loop);
    }

    private static void signup(UserManager userManager, Scanner sc) {
        boolean inputValid = false;
        String username;
        String password;

        do {
            System.out.print("Enter your new username: ");
            username = sc.nextLine();
            if (userManager.findUser(username) != null) {
                System.out.println("Username already exists. Please choose a different username.");
            } else {
                inputValid = true;
            }
        } while (!inputValid);

        do {
            inputValid = false;
            System.out.println("Password must be 8 or more characters long, with at least 1 uppercase, 1 lowercase, 1 digit, and 1 special character.");
            System.out.print("Enter your password: ");
            password = sc.nextLine();
            inputValid = userManager.isValidPassword(password);
            

        } while(!inputValid);

        UserBase newUser = new Customer(username, password);
        userManager.addUser(newUser);
        System.out.println("User registered successfully.");
    }
}