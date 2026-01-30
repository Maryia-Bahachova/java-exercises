package lt.esdc.designpatterns.handlers;

import java.util.Arrays;

public class ParsingHandler extends OrderHandler {

    @Override
    public void handle(OrderContext context) {
        if (context.getInput() == null || context.getInput().isBlank()) {
            if (next != null) next.handle(context);
            return;
        }

        String[] parts = context.getInput().trim().split("\\s+");

        if (parts.length == 0) return;

        int coffeeIndex = 0;
        String discount = "none";

        if (parts[0].equalsIgnoreCase("student") ||
                parts[0].equalsIgnoreCase("loyalty")) {
            discount = parts[0].toLowerCase();
            coffeeIndex = 1;
        }

        context.setDiscount(discount);
        context.setCoffeeType(parts[coffeeIndex].toLowerCase());

        if (parts.length > coffeeIndex + 1) {
            context.setToppings(Arrays.asList(parts).
                    subList(coffeeIndex + 1, parts.length));
        }
        if (next != null) next.handle(context);
    }
}