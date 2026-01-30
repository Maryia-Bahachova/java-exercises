package lt.esdc.designpatterns.decorator;

import lt.esdc.designpatterns.product.Coffee;
import lt.esdc.designpatterns.product.CoffeeDrink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorsTest {

    @Test
    void testCaramelDecorator() {
        CoffeeDrink coffee = new Coffee.Builder().water(50).coffee(18).milk(0).build();
        CoffeeDrink caramel = new Caramel(coffee);
        assertEquals("50ml 18g 0ml caramel", caramel.prepareCommand());
    }

    @Test
    void testCreamAndLiquorDecorator() {
        CoffeeDrink coffee = new Coffee.Builder().water(60).coffee(16).milk(0).build();
        CoffeeDrink decorated = new Liquor(new Cream(coffee));
        assertEquals("60ml 16g 0ml cream liquor", decorated.prepareCommand());
    }
}