import java.util.Scanner;
import java.util.InputMismatchException;

public class Customer extends UserBase {

    private Cart cart;

    public Customer(String username, String password) {
        super(username, password, "Customer");
        this.cart = new Cart();
    }

    @Override
    public void accessControl(Shop shop, Scanner sc) {
        boolean loop = true;
        while (loop) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Remove Item from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View orders history");
            System.out.println("7. Log out");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();  // Clear the buffer
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
                    if (shop.getItems() == null || shop.getItems().isEmpty()) {
                        System.out.println("There are no items available for sale. Please try again later.");
                    } else {
                        shop.listItems();
                        System.out.print("Enter item ID to add to cart: ");
                        int itemId;
                        try {
                            itemId = sc.nextInt();
                            sc.nextLine();  // Clear the buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.nextLine();
                            continue;
                        }


                        Item item = shop.getItemById(itemId);
                        if (item != null) {
                            System.out.println("Available quantity for " + item.getName() + ": " + item.getQuantity());

                            System.out.print("Enter quantity to add to cart: ");
                            int quantity;
                            try {
                                quantity = sc.nextInt();
                                sc.nextLine();  // Clear the buffer
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid quantity number.");
                                sc.nextLine();
                                continue;
                            }

                            if (quantity > 0 && quantity <= item.getQuantity()) {  // Validate requested quantity
                                cart.addItem(item, quantity);
                                System.out.println("Added " + quantity + " of " + item.getName() + " to the cart.");
                                System.out.println("Remaining quantity of " + item.getName() + ": " + item.getQuantity());  // Display remaining stock
                            } else if (quantity > item.getQuantity()) {
                                System.out.println("Requested quantity exceeds available stock. Please enter a quantity less than or equal to " + item.getQuantity() + ".");
                            } else {
                                System.out.println("Invalid quantity. Please enter a positive number.");
                            }
                        } else {
                            System.out.println("Invalid item ID.");
                        }
                    }
                    break;
                case 3:
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Your cart is empty. Please add items first.");
                    } else {
                        cart.viewCart();
                        System.out.print("Enter item ID to remove from cart: ");
                        int removeItemId = sc.nextInt();
                        cart.removeItem(removeItemId);
                    }
                    break;
                case 4:
                    cart.viewCart();
                    break;
                case 5:
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Your cart is empty. Please add items to proceed.");
                        break;
                    } else {
                        boolean loop2 = true;
                        while (loop2) {
                            boolean paymentStatus = false;
                            Payment payment = new Payment(cart.getTotalAmount());
                            payment.showPaymentDetails();
                            System.out.println("Payment Methods:");
                            System.out.println("1. Credit Card");
                            System.out.println("2. TnG");
                            System.out.println("3. Online Payment");
                            System.out.println("4. Abort");
                            System.out.print("Enter your choice: ");
                            int paymentMethod;
                            try {
                                paymentMethod = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                sc.nextLine();
                                continue;
                            }

                            switch (paymentMethod) {
                                case 1:
                                    System.out.print("Enter your credit card number: ");
                                    String creditCardNumber = sc.nextLine();
                                    System.out.print("Enter your expiry date (MM/YY): ");
                                    String expiryDate = sc.nextLine();
                                    System.out.print("Enter your CVV: ");
                                    String cvv = sc.nextLine();
                                    PaymentType creditPay = new CreditPay(creditCardNumber, expiryDate, cvv);
                                    paymentStatus = payment.processPayment(creditPay);
                                    break;
                                case 2:
                                    System.out.print("Enter your phone number: ");
                                    String phoneNumber = sc.nextLine();
                                    System.out.print("Enter your 6-digit passcode: ");
                                    String passcode = sc.nextLine();
                                    PaymentType tngPay = new TnGPay(phoneNumber, passcode);
                                    paymentStatus = payment.processPayment(tngPay);
                                    break;
                                case 3:
                                    System.out.print("Enter your bank account number: ");
                                    String bankAccountNumber = sc.nextLine();
                                    System.out.print("Enter your bank account password: ");
                                    String bankAccountPassword = sc.nextLine();
                                    PaymentType onlinePay = new OnlinePay(bankAccountNumber, bankAccountPassword);
                                    paymentStatus = payment.processPayment(onlinePay);
                                    break;
                                case 4:
                                    System.out.println("Payment aborted.");
                                    loop2 = false;
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    continue;
                            }

                            if (paymentStatus) {
                                System.out.println("Payment successful. Thank you for shopping with us.");
                                Order order = new Order(username, cart.getItems(),cart.getQuantities());
                                shop.getOrderManager().addOrder(order);
                                cart.checkout(shop);
                                loop2 = false;  // Exit payment loop after successful payment
                            }
                        }
                    }
                    break;
                case 6:
                    // view order history
                    shop.getOrderManager().viewOrderHistory(username);
                    break;
                case 7:
                    loop = false;  // Exit customer menu loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public Cart getCart() {
        return cart;
    }
}
