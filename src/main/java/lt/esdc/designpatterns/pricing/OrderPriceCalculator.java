package lt.esdc.designpatterns.pricing;

import lt.esdc.designpatterns.product.CoffeeDrink;

public class OrderPriceCalculator {

    private final DiscountStrategy discountStrategy;

    public OrderPriceCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculate(CoffeeDrink drink) {
        return discountStrategy.apply(drink.getPrice());
    }
}