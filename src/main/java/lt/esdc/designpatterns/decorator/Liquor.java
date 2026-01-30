package lt.esdc.designpatterns.decorator;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class Liquor extends CoffeeDecorator {
    public Liquor(CoffeeDrink coffee) {
        super(coffee,0.7);
    }
    @Override
    public String prepareCommand() {
        return coffee.prepareCommand() + " liquor";
    }
}
