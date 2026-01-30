package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.Coffee;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class ItalianCoffeeFactory implements CoffeeFactory {

    @Override
    public CoffeeDrink espresso() {
        return new Coffee.Builder()
                .water(50)
                .coffee(18)
                .milk(0)
                .price(100.00)
                .build();
    }

    @Override
    public CoffeeDrink cappuccino() {
        return new Coffee.Builder()
                .water(200)
                .coffee(15)
                .milk(100)
                .price(56.00)
                .build();
    }

    @Override
    public CoffeeDrink latte() {
        return new Coffee.Builder()
                .water(250)
                .coffee(15)
                .milk(200)
                .price(80.00)
                .build();
    }
}