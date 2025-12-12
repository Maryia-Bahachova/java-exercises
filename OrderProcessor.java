package edsc.maryiabahachova.lab1;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private List<String> products = new ArrayList<>();
    private double total;
    private String customerEmail;
    private String customerType;
    private boolean discountApplied;
    private String paymentMethod;

    public void processOrder(String email, String type, String payment, List<String> products) {
        this.customerEmail = email;
        this.customerType = type;
        this.paymentMethod = payment;
        this.products = products;
        this.total = 0;

        for (String p : products) {
            if (p.equals("book")) {
                total += 12.99;
            } else if (p.equals("laptop")) {
                total += 999.99;
            } else if (p.equals("pen")) {
                total += 1.5;
            } else {
                total += 5;
            }
        }

        if (type.equals("VIP")) {
            total = total * 0.9;
            discountApplied = true;
        } else if (type.equals("STUDENT")) {
            total = total * 0.95;
            discountApplied = true;
        } else {
            discountApplied = false;
        }

        if (payment.equals("CREDIT_CARD")) {
            System.out.println("Charging credit card for $" + total);
        } else if (payment.equals("PAYPAL")) {
            System.out.println("Processing PayPal payment for $" + total);
        } else if (payment.equals("BANK_TRANSFER")) {
            System.out.println("Waiting for bank transfer of $" + total);
        }

        sendEmail(email, total, discountApplied);
        logOrder();
    }

    private void sendEmail(String email, double total, boolean discount) {
        String msg = "Thank you for your order! Total: $" + total;
        if (discount) {
            msg += " (discount applied)";
        }
        System.out.println("Sending email to " + email + ": " + msg);
    }

    private void logOrder() {
        System.out.println("Saving order to database (not implemented).");
    }

    public void cancelOrder(String email) {
        System.out.println("Cancelling order for " + email);
        if (email.endsWith("@vip.com")) {
            System.out.println("Refunding with special VIP procedure");
        }
    }

    public void printSummary() {
        System.out.println("Customer: " + customerEmail);
        System.out.println("Type: " + customerType);
        System.out.println("Total: " + total);
        System.out.println("Payment: " + paymentMethod);
        System.out.println("Discount applied: " + discountApplied);
    }
}