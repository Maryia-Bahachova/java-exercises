package lt.esdc.designpatterns.discount;

import lt.esdc.designpatterns.pricing.LoyaltyDiscountStrategy;
import lt.esdc.designpatterns.pricing.NoDiscountStrategy;
import lt.esdc.designpatterns.pricing.StudentDiscountStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DiscountTest {

    @Test
    void testNoDiscountStrategy() {
        NoDiscountStrategy strategy = new NoDiscountStrategy();
        double price = strategy.apply(10.0);
        assertEquals(10.0, price, 0.001);
    }

    @Test
    void testStudentDiscountStrategy() {
        StudentDiscountStrategy strategy = new StudentDiscountStrategy();
        double price = strategy.apply(10.0);
        assertEquals(8.0, price, 0.001); // 20% off
    }

    @Test
    void testLoyaltyDiscountStrategy() {
        LoyaltyDiscountStrategy strategy = new LoyaltyDiscountStrategy();
        double price = strategy.apply(10.0);
        assertEquals(9.0, price, 0.001); // 10% off
    }
}