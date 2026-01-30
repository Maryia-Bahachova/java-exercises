package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.handlers.*;
import lt.esdc.designpatterns.listener.OrderEvent;
import lt.esdc.designpatterns.listener.OrderListener;
import lt.esdc.designpatterns.machine.CoffeeMachineClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineControllerImpl implements CoffeeMachineController {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineControllerImpl.class);

    private final CoffeeMachineClient machine;
    private final CoffeeFactory factory;
    private final List<OrderListener> listeners = new ArrayList<>();

    public CoffeeMachineControllerImpl(CoffeeMachineClient machine, CoffeeFactory factory) {
        this.machine = machine;
        this.factory = factory;
    }

    @Override
    public void processOrder(String[] orders) {

        for (String order : orders) {
            try {
                OrderContext context = new OrderContext(order);
                OrderHandler parser = new ParsingHandler();
                OrderHandler creator = new CoffeeCreationHandler(factory);
                OrderHandler toppings = new ToppingHandler();
                OrderHandler discount = new DiscountHandler();
                OrderHandler sender = new MachineHandler(machine);

                parser
                        .linkWith(creator)
                        .linkWith(toppings)
                        .linkWith(discount)
                        .linkWith(sender);

                parser.handle(context);

                notifyListeners(new OrderEvent(order, true, context.getFinalPrice()));

            } catch (Exception e) {
                notifyListeners(new OrderEvent(order, false, 0));
                logger.error(String.valueOf(e));
            }
        }
    }

    @Override
    public void addListener(OrderListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(OrderEvent event) {
        listeners.forEach(l -> l.onOrderProcessed(event));
    }
}