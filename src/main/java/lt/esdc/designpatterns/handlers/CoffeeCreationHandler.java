package lt.esdc.designpatterns.handlers;

import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class CoffeeCreationHandler extends OrderHandler {

    private final CoffeeFactory factory;

    public CoffeeCreationHandler(CoffeeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void handle(OrderContext context) {
        CoffeeDrink coffee = factory.create(context.getCoffeeType());

        context.setCoffee(coffee);
        context.setFinalPrice(coffee.getPrice());

        if (next != null) next.handle(context);
    }
}