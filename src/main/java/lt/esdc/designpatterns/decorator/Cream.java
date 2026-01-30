package lt.esdc.designpatterns.decorator;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class Cream extends CoffeeDecorator {
    public Cream(CoffeeDrink coffee) {
        super(coffee, 0.6);
    }
    @Override
    public String prepareCommand() {
        return coffee.prepareCommand() + " cream";
    }
}
