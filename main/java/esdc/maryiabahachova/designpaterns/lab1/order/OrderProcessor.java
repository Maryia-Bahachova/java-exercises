package esdc.maryiabahachova.designpaterns.lab1.order;
import esdc.maryiabahachova.designpaterns.lab1.customerservice.Customer;
import esdc.maryiabahachova.designpaterns.lab1.customerservice.EmailService;
import esdc.maryiabahachova.designpaterns.lab1.discount.Discount;
import esdc.maryiabahachova.designpaterns.lab1.discount.DiscountApplying;
import esdc.maryiabahachova.designpaterns.lab1.discount.DiscountService;
import esdc.maryiabahachova.designpaterns.lab1.payment.PaymentService;
import esdc.maryiabahachova.designpaterns.lab1.payment.PaymentType;

import java.util.List;

public class OrderProcessor implements Logging, DiscountApplying {
    private final PaymentService paymentService;
    private final EmailService emailService;
    private final DiscountService discountService;

    public OrderProcessor(PaymentService paymentService, EmailService emailService, DiscountService discountService) {
        this.paymentService = paymentService;
        this.emailService = emailService;
        this.discountService = discountService;
    }

    public void processOrder(Customer customer, PaymentType paymentType, List<Product> products) {
        double total = calculateTotal(products);

        Discount discount = discountService.getDiscountForCustomer(customer);
        total = applyDiscount(discount, total);

        System.out.println("Your discount is " + (discount != null ? discount.name() : "none") +
                ", your total after discount is $" + total);

        paymentService.charge(paymentType, total);
        emailService.sendEmail(customer, total, discount);

        logOrder(customer, total, discount, paymentType);
    }

    private double calculateTotal(List<Product> products) {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public static void cancelOrder(Customer customer) {
        System.out.println("Cancelling order for " + customer.getCustomerEmail());
        if (customer.getCustomerEmail().endsWith("@vip.com")) {
            System.out.println("Refunding with special VIP procedure");
        }
    }
}