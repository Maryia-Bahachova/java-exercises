package lt.esdc.designpatterns.pricing;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double totalPrice) {
        return totalPrice;
    }
}