package esdc.maryiabahachova.designpaterns.lab1.customerservice;

import esdc.maryiabahachova.designpaterns.lab1.discount.Discount;

public class EmailService {
    public void sendEmail(Customer customer, double total, Discount discount) {
        System.out.println("Sending email to " + customer.getCustomerEmail() +
                ": Thank you for your order! Total: $" + total +
                (discount != null ? " (discount applied)" : ""));
    }
}
