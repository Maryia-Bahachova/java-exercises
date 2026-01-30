package lt.esdc.designpatterns.discount;

import lt.esdc.designpatterns.pricing.LoyaltyDiscountStrategy;
import lt.esdc.designpatterns.pricing.NoDiscountStrategy;
import lt.esdc.designpatterns.pricing.OrderPriceCalculator;
import lt.esdc.designpatterns.pricing.StudentDiscountStrategy;
import lt.esdc.designpatterns.product.CoffeeDrink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderPriceCalculatorTest {

    private CoffeeDrink coffee;

    @BeforeEach
    void setup() {
        coffee = mock(CoffeeDrink.class);
    }

    @Test
    void testCalculate_noDiscount() {
        when(coffee.getPrice()).thenReturn(5.0);

        OrderPriceCalculator calculator = new OrderPriceCalculator(new NoDiscountStrategy());
        double price = calculator.calculate(coffee);

        assertEquals(5.0, price, 0.001);
    }

    @Test
    void testCalculate_studentDiscount() {
        when(coffee.getPrice()).thenReturn(10.0);

        OrderPriceCalculator calculator = new OrderPriceCalculator(new StudentDiscountStrategy());
        double price = calculator.calculate(coffee);

        assertEquals(8.0, price, 0.001); // 20% off
    }

    @Test
    void testCalculate_loyaltyDiscount() {
        when(coffee.getPrice()).thenReturn(10.0);

        OrderPriceCalculator calculator = new OrderPriceCalculator(new LoyaltyDiscountStrategy());
        double price = calculator.calculate(coffee);

        assertEquals(9.0, price, 0.001); // 10% off
    }

    @Test
    void testCalculate_zeroPrice() {
        when(coffee.getPrice()).thenReturn(0.0);

        OrderPriceCalculator calculator = new OrderPriceCalculator(new StudentDiscountStrategy());
        double price = calculator.calculate(coffee);

        assertEquals(0.0, price, 0.001);
    }

    @Test
    void testCalculate_negativePrice() {
        when(coffee.getPrice()).thenReturn(-5.0);

        OrderPriceCalculator calculator = new OrderPriceCalculator(new LoyaltyDiscountStrategy());
        double price = calculator.calculate(coffee);

        assertEquals(-4.5, price, 0.001); // 10% off negative price
    }
}