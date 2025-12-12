package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.product.Coffee;

public interface CoffeeFactory {
    Coffee.Builder espressoBuilder();
    Coffee.Builder cappuccinoBuilder();
    Coffee.Builder latteBuilder();
}
