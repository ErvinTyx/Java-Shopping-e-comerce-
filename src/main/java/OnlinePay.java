import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class OnlinePay extends PaymentType {

    private String banknumber;
    private String bankAccountPassword;

    public OnlinePay(String banknumber, String bankAccountPassword) {
        super("OnlinePay");
        this.banknumber = banknumber;
        this.bankAccountPassword = bankAccountPassword;
    }

    @Override
    public boolean processPayment(double totalAmount) {
        boolean status = false; // variable to store payment status
        try {
            Scanner sc = new Scanner(new File("bank.txt"));
            StringBuilder sb = new StringBuilder();
            boolean found = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(banknumber) && data[1].equals(bankAccountPassword)) {
                    double amount = Double.parseDouble(data[2]);
                    if (amount >= totalAmount) {
                        System.out.println("Payment successful.");
                        amount -= totalAmount;
                        data[2] = String.valueOf(amount);
                        sb.append(String.join(",", data));
                        found = true;
                        status = true;
                    } else {
                        System.out.println("Insufficient balance.");
                        status = false;
                    }
                } else {
                    sb.append(line);
                }
                sb.append("\r\n"); // update to append a new line
            }
            if (!found) {
                System.out.println("Invalid account number or password.");
                status = false;
            }
            FileWriter fw = new FileWriter("bank.txt");
            fw.write(sb.toString());
            fw.close();
            sc.close();
        } catch (IOException e) {
            status = false;
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
        
        return status;
    }

}