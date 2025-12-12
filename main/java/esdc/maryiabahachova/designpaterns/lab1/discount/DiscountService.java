package esdc.maryiabahachova.designpaterns.lab1.discount;

import esdc.maryiabahachova.designpaterns.lab1.customerservice.Customer;

public class DiscountService {
    public Discount getDiscountForCustomer(Customer customer) {
        if (customer == null || customer.getCustomerType() == null) {
            return null;
        }

        switch (customer.getCustomerType().toUpperCase()) {
            case "VIP":
                return Discount.VIP;
            case "STUDENT":
                return Discount.STUDENT;
            default:
                return null;
        }
    }
}