package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.Coffee;

public class LithuanianCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee.Builder espressoBuilder() {
        return new Coffee.Builder()
                .water(60)
                .coffee(16)
                .milk(0);
    }

    @Override
    public Coffee.Builder cappuccinoBuilder() {
        return new Coffee.Builder()
                .water(200)
                .coffee(15)
                .milk(120);
    }

    @Override
    public Coffee.Builder latteBuilder() {
        return new Coffee.Builder()
                .water(240)
                .coffee(15)
                .milk(220);
    }
}