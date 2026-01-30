package lt.esdc.designpatterns.handlers;

import lt.esdc.designpatterns.pricing.*;

public class DiscountHandler extends OrderHandler {

    @Override
    public void handle(OrderContext context) {

        double priceWithToppings = context.getFinalPrice();

        DiscountStrategy strategy = switch (context.getDiscount().toLowerCase()) {
            case "student" -> new StudentDiscountStrategy();
            case "loyalty" -> new LoyaltyDiscountStrategy();
            default -> new NoDiscountStrategy();
        };

        context.setFinalPrice(strategy.apply(priceWithToppings));

        if (next != null) next.handle(context);
    }
}