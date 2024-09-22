
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
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Log out");
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
                    shop.listItems();
                    break;
                case 2:
                    if(shop.getItems().isEmpty()){
                        System.out.println("There are not items available now for sale. Please try again later.");
                    }else{
                        System.out.print("Enter item ID to add to cart:");
                        int itemId = sc.nextInt();
                        
                        Item item = shop.getItemById(itemId);
                        if (item != null) {
                            cart.addItem(item);
                            System.out.println("Item added to cart: " + item.getName());
                        } else {
                            System.out.println("Invalid item ID.");
                        }
                    }
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    if(cart.getItems().isEmpty()){
                        System.out.println("Your cart is empty. Please add items to proceed.");
                        break;
                    }else{
                        boolean  loop2 = true;
                        while(loop2){
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
                                    System.out.println("Enter your 6 digit passcode: ");
                                    String passcode = sc.nextLine();
                                    PaymentType tngPay = new TnGPay(phoneNumber, passcode);
                                    paymentStatus = payment.processPayment(tngPay);
                                    break;
                                case 3:
                                    System.out.println("Enter your bank account number: ");
                                    String bankAccountNumber = sc.nextLine();
                                    System.out.println("Enter your bank account password: ");
                                    String bankAccountPassword = sc.nextLine();
                                    PaymentType onlinePay = new OnlinePay(bankAccountNumber, bankAccountPassword);
                                    paymentStatus = payment.processPayment(onlinePay);
                                    break;
                                case 4:
                                    loop2 = false;
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }

                            if(paymentStatus){  
                                System.out.println("Payment successful. Thank you for shopping with us.");
                                cart.checkout();
                                loop2 = false;
                            }
                        }
                    }
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
