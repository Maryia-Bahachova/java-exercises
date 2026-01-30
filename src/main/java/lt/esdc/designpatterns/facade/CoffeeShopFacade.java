package lt.esdc.designpatterns.facade;

import lt.esdc.designpatterns.controller.CoffeeMachineController;
import lt.esdc.designpatterns.controller.CoffeeMachineControllerImpl;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.factory.ItalianCoffeeFactory;
import lt.esdc.designpatterns.factory.LithuanianCoffeeFactory;
import lt.esdc.designpatterns.listener.OrderListener;
import lt.esdc.designpatterns.machine.*;
import lt.esdc.designpatterns.proxy.CachedTokenCoffeeMachineProxy;

public class CoffeeShopFacade {

    private final CoffeeMachineController controller;

    public CoffeeShopFacade(String region, String version) {

        CoffeeFactory factory = switch (region.toLowerCase()) {
            case "italy" -> new ItalianCoffeeFactory();
            case "lithuania" -> new LithuanianCoffeeFactory();
            default -> throw new IllegalArgumentException("Unknown region: " + region);
        };

        CoffeeMachineClient machine = switch (version.toLowerCase()) {
            case "v15" -> new CoffeeMachineAdapterV15(CoffeeMachineConnector.getInstance());
            case "v75" -> {CoffeeMachineV75 proxy = new CachedTokenCoffeeMachineProxy(NewCoffeeMachineConnector.getInstance());
                yield new CoffeeMachineAdapterV75(proxy);
            }

            default -> throw new IllegalArgumentException("Unknown machine version: " + version
            );
        };

        this.controller = new CoffeeMachineControllerImpl(machine, factory);
    }

    public void processOrder(String orderLine) {
        controller.processOrder(new String[]{orderLine});
    }
    public void addListener(OrderListener listener) {
        controller.addListener(listener);
    }
}