
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
        System.out.println("Processing credit card payment with card number: " + creditCardNumber);
        if (isValidCardNumber(creditCardNumber) && isValidExpiryDate(expiryDate) && isValidCVV(cvv)) {
            System.out.println("Payment successful.");
            return true;
        } else {
            System.out.println("Payment failed. Please check your card information.");
            return false;
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    private boolean isValidExpiryDate(String expiryDate) {
        if (expiryDate.matches("[0-9]{2}/[0-9]{2}")) {
            String[] parts = expiryDate.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);

            if (month >= 1 && month <= 12 && year >= 0 && year <= 99) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCVV(String cvv) {
        if (cvv.matches("[0-9]{3}")) {
            return true;
        }
        return false;
    }

}
