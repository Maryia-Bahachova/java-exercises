package esdc.maryiabahachova.designpaterns.lab1.order;

import esdc.maryiabahachova.designpaterns.lab1.customerservice.Customer;
import esdc.maryiabahachova.designpaterns.lab1.discount.Discount;
import esdc.maryiabahachova.designpaterns.lab1.payment.PaymentType;

public interface Logging {
    default void logOrder(Customer customer, double total, Discount discount, PaymentType paymentType) {
        System.out.println("Saving order to database (not implemented).");
        System.out.println("Customer: " + customer.getCustomerEmail());
        System.out.println("Payment method: " + paymentType);
        System.out.println("Discount applied: " + (discount != null));
        System.out.println("Final total: $" + total);
    }
}