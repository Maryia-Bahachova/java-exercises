package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.CoffeeDrink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeFactoryTest {

    @Test
    void testItalianFactory() {
        CoffeeFactory factory = new ItalianCoffeeFactory();

        CoffeeDrink espresso = factory.espresso();
        assertEquals("50ml 18g 0ml", espresso.prepareCommand());

        CoffeeDrink cappuccino = factory.cappuccino();
        assertEquals("200ml 15g 100ml", cappuccino.prepareCommand());
    }

    @Test
    void testLithuanianFactory() {
        CoffeeFactory factory = new LithuanianCoffeeFactory();

        CoffeeDrink latte = factory.latte();
        assertEquals("240ml 15g 220ml", latte.prepareCommand());
    }

    @Test
    void testCreateMethod() {
        CoffeeFactory factory = new ItalianCoffeeFactory();
        CoffeeDrink drink = factory.create("latte");
        assertEquals("250ml 15g 200ml", drink.prepareCommand());
    }
}