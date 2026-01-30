package lt.esdc.designpatterns.handlers;

import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.product.Coffee;
import lt.esdc.designpatterns.product.CoffeeDrink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ChainHandlersTest {

    private CoffeeFactory factory;

    @BeforeEach
    void setUp() {
        factory = mock(CoffeeFactory.class);
    }

    @Test
    void testChainHandlesFullOrder() {
        CoffeeDrink coffee = new Coffee.Builder()
                .water(250)
                .coffee(15)
                .milk(200)
                .build();

        when(factory.create("latte")).thenReturn(coffee);

        ParsingHandler parser = new ParsingHandler();
        CoffeeCreationHandler creator = new CoffeeCreationHandler(factory);
        ToppingHandler toppings = new ToppingHandler();
        DiscountHandler discount = new DiscountHandler();

        parser.linkWith(creator)
                .linkWith(toppings)
                .linkWith(discount);

        OrderContext context = new OrderContext("latte cream caramel");
        parser.handle(context);

        CoffeeDrink resultCoffee = context.getCoffee();
        assertNotNull(resultCoffee);

        String command = resultCoffee.prepareCommand();
        assertTrue(command.contains("250ml 15g 200ml"));
        assertTrue(command.contains("cream"));
        assertTrue(command.contains("caramel"));
    }

    @Test
    void testChainHandlesUnknownCoffee() {
        when(factory.create("unknown")).thenThrow(new IllegalArgumentException());

        OrderHandler parser = new ParsingHandler();
        CoffeeCreationHandler creator = new CoffeeCreationHandler(factory);

        parser.linkWith(creator);

        OrderContext context = new OrderContext("unknown cream");

        assertThrows(IllegalArgumentException.class, () -> parser.handle(context));
    }

    @Test
    void testChainHandlesOnlyParsing() {
        OrderHandler parser = new ParsingHandler();
        OrderContext context = new OrderContext("espresso");

        parser.handle(context);

        assertNull(context.getCoffee());
    }
}