package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.Coffee;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class LithuanianCoffeeFactory implements CoffeeFactory {

    @Override
    public CoffeeDrink espresso() {
        return new Coffee.Builder()
                .water(60)
                .coffee(16)
                .milk(0)
                .price(100.00)
                .build();
    }

    @Override
    public CoffeeDrink cappuccino() {
        return new Coffee.Builder()
                .water(200)
                .coffee(15)
                .milk(120)
                .price(56.00)
                .build();
    }

    @Override
    public CoffeeDrink latte() {
        return new Coffee.Builder()
                .water(240)
                .coffee(15)
                .milk(220)
                .price(80.00)
                .build();
    }
}