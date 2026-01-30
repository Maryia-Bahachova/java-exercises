package lt.esdc.designpatterns.pricing;

public class LoyaltyDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.9;
    }
}