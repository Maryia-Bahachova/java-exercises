package lt.esdc.designpatterns.pricing;

public class StudentDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.8;
    }
}