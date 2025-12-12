package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.Coffee;
public class ItalianCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee.Builder espressoBuilder() {
        return new Coffee.Builder()
                .water(50)
                .coffee(18)
                .milk(0);
    }

    @Override
    public Coffee.Builder cappuccinoBuilder() {
        return new Coffee.Builder()
                .water(200)
                .coffee(15)
                .milk(100);
    }

    @Override
    public Coffee.Builder latteBuilder() {
        return new Coffee.Builder()
                .water(250)
                .coffee(15)
                .milk(200);
    }
}