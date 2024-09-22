import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class TnGPay extends PaymentType {

    private String phoneNumber;
    private String passcode;

    public TnGPay(String phoneNumber, String passcode) {
        super("TnGPay");
        this.phoneNumber = phoneNumber;
        this.passcode = passcode;
    }

    @Override
    public boolean processPayment(double totalAmount) {
        boolean status = false; 
        System.out.println("Processing TnG payment with phone number: " + phoneNumber);
        try {
            Scanner sc = new Scanner(new File("tnG.txt"));
            StringBuilder sb = new StringBuilder();
            boolean found = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(phoneNumber) && data[1].equals(passcode)) {
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
                        status =false;
                    }
                } else {
                    sb.append(line);
                }
                sb.append("\r\n"); // update to append a new line
            }
            if (!found) {
                System.out.println("Invalid phone number or passcode.");
                status = false;
            }
            FileWriter fw = new FileWriter("tnG.txt");
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
