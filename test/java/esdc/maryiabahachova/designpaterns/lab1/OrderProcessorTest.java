package esdc.maryiabahachova.designpaterns.lab1;
import esdc.maryiabahachova.designpaterns.lab1.customerservice.Customer;
import esdc.maryiabahachova.designpaterns.lab1.customerservice.EmailService;
import esdc.maryiabahachova.designpaterns.lab1.discount.DiscountService;
import esdc.maryiabahachova.designpaterns.lab1.order.OrderProcessor;
import esdc.maryiabahachova.designpaterns.lab1.order.Product;
import esdc.maryiabahachova.designpaterns.lab1.payment.PaymentService;
import esdc.maryiabahachova.designpaterns.lab1.payment.PaymentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderProcessorTest{
    PaymentService paymentService = new PaymentService();
    EmailService emailService = new EmailService();
    DiscountService discountService = new DiscountService();
    OrderProcessor processor = new OrderProcessor(paymentService, emailService,  discountService);

    @Test
    void testProcessOrderWithVIPDiscount() {
        Customer customer = new Customer("john@vip.com", "VIP");

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(5.99, "pen"),
                new Product(94.01, "headphones")
        ));

        processor.processOrder(customer, PaymentType.CREDIT_CARD, products);
    }

    @Test
    void testProcessOrderWithStudentDiscount() {
        Customer customer = new Customer("yara@gmail.com", "student");

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(5.99, "pen"),
                new Product(94.01, "headphones")
        ));

        processor.processOrder(customer, PaymentType.CREDIT_CARD, products);
    }

    @Test
    void testProcessOrderWithoutDiscount() {
            Customer customer = new Customer("john@gmail.com", "basic");

            List<Product> products = new ArrayList<>(Arrays.asList(
                    new Product(12.99, "book"),
                    new Product(12.34, "notebook")
            ));

            processor.processOrder(customer, PaymentType.CREDIT_CARD, products);
        }

    @Test
    void testCancellingOrder() {
        Customer customer = new Customer("john@gmail.com", "basic");

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(12.99, "book"),
                new Product(1298.9, "laptop")
        ));

        processor.processOrder(customer, PaymentType.PAYPAL, products);
        OrderProcessor.cancelOrder(customer);
    }
}