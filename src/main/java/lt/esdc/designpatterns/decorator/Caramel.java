package lt.esdc.designpatterns.decorator;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class Caramel extends CoffeeDecorator {

    public Caramel(CoffeeDrink coffee) {
        super(coffee, 0.5);
    }

    @Override
    public String prepareCommand() {
        return coffee.prepareCommand() + " caramel";
    }
}
