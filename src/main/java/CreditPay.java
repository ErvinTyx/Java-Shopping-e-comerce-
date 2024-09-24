import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreditPay extends PaymentType {

    private String creditCardNumber;
    private String expiryDate;
    private String cvv;

    public CreditPay(String creditCardNumber, String expiryDate, String cvv) {
        super("CreditPay");
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double totalAmount) {
        boolean status = false; 
        System.out.println("Processing credit card payment with card number: " + creditCardNumber);
        try {
            Scanner sc = new Scanner(new File("creditCard.txt"));
            StringBuilder sb = new StringBuilder();
            boolean found = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(creditCardNumber) && data[1].equals(expiryDate) && data[2].equals(cvv)) {
                    double amount = Double.parseDouble(data[3]);
                    if (amount >= totalAmount) {
                        amount -= totalAmount;
                        data[3] = String.valueOf(amount);
                        sb.append(String.join(",", data));
                        found = true;
                        status = true;
                    } else {
                        System.out.println("Insufficient balance.");
                        status =false;
                    }
                } else {
                    sb.append(line);
                }
                sb.append("\r\n"); // update to append a new line
            }
            if (!found) {
                System.out.println("Invalid card information.");
                status = false;
            }
            FileWriter fw = new FileWriter("creditCard.txt");
            fw.write(sb.toString());
            fw.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
        return status;
    }


}
