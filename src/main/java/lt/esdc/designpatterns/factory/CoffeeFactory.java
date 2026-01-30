package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.CoffeeDrink;

public interface CoffeeFactory {
    CoffeeDrink espresso();
    CoffeeDrink cappuccino();
    CoffeeDrink latte();

    default CoffeeDrink create(String order) {
        return switch (order.toLowerCase()) {
            case "espresso" -> espresso();
            case "cappuccino" -> cappuccino();
            case "latte" -> latte();
            default -> throw new IllegalArgumentException("Unknown coffee: " + order);
        };
    }
}
