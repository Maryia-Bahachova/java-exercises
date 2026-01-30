package lt.esdc.designpatterns.handlers;

import lt.esdc.designpatterns.machine.CoffeeMachineClient;
import lt.esdc.designpatterns.product.CoffeeDrink;

public class MachineHandler extends OrderHandler {

    private final CoffeeMachineClient machine;

    public MachineHandler(CoffeeMachineClient machine) {
        this.machine = machine;
    }

    @Override
    public void handle(OrderContext context) {
        CoffeeDrink coffee = context.getCoffee();

        if (coffee == null) {
            throw new IllegalStateException("Coffee is not created");
        }

        machine.prepare(coffee.prepareCommand());
        goNext(context);
    }
}
