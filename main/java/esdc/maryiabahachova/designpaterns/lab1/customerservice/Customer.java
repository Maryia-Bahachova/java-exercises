package esdc.maryiabahachova.designpaterns.lab1.customerservice;

public class Customer {
    private final String customerEmail;
    private final String customerType;

    public Customer(String customerEmail, String customerType) {
        this.customerEmail = customerEmail;
        this.customerType = customerType;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerType() {
        return customerType;
    }

}