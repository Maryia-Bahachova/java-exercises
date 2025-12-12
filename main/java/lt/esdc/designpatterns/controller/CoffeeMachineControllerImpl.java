package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineV15;
import lt.esdc.designpatterns.product.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeMachineControllerImpl implements CoffeeMachineController {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineControllerImpl.class);

    private final CoffeeMachineV15 machine;
    private final CoffeeFactory factory;

    public CoffeeMachineControllerImpl(CoffeeMachineV15 machine, CoffeeFactory factory) {
        this.machine = machine;
        this.factory = factory;
    }

    @Override
    public void processOrder(String[] orders) {

        for (String drink : orders) {

            Coffee coffee;

            switch (drink.toLowerCase()) {
                case "espresso":
                    coffee = factory.espressoBuilder().build();
                    break;
                case "cappuccino":
                    coffee = factory.cappuccinoBuilder().build();
                    break;
                case "latte":
                    coffee = factory.latteBuilder().build();
                    break;
                default:
                    logger.error("Unknown drink: {}", drink);
                    continue;
            }

            machine.send(coffee.prepareCommand());
        }
    }
}