package lt.esdc.designpatterns.decorator;

import lt.esdc.designpatterns.product.CoffeeDrink;

public abstract class CoffeeDecorator implements CoffeeDrink {
    protected final CoffeeDrink coffee;
    protected final double toppingPrice;

    protected CoffeeDecorator(CoffeeDrink coffee, double toppingPrice) {
        this.coffee = coffee;
        this.toppingPrice = toppingPrice;
    }

    @Override
    public String prepareCommand() {
        return coffee.prepareCommand();
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + toppingPrice;
    }
}