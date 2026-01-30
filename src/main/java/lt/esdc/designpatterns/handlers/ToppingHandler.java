package lt.esdc.designpatterns.handlers;

import lt.esdc.designpatterns.decorator.Caramel;
import lt.esdc.designpatterns.decorator.Cream;
import lt.esdc.designpatterns.decorator.Liquor;
import lt.esdc.designpatterns.product.CoffeeDrink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToppingHandler extends OrderHandler {
    private static final Logger logger = LoggerFactory.getLogger(ToppingHandler.class);

    @Override
    public void handle(OrderContext context) {
        CoffeeDrink coffee = context.getCoffee();
        if (coffee == null)
            throw new IllegalStateException("Coffee must be created before adding toppings");

        for (String topping : context.getToppings()) {
            coffee = switch (topping.toLowerCase()) {
                case "caramel" -> new Caramel(coffee);
                case "cream" -> new Cream(coffee);
                case "liquor" -> new Liquor(coffee);
                default -> {
                    logger.warn("Unknown topping: {}", topping);
                    yield coffee;
                }
            };
        }

        context.setCoffee(coffee);

        context.setFinalPrice(coffee.getPrice());

        if (next != null) next.handle(context);
    }
}