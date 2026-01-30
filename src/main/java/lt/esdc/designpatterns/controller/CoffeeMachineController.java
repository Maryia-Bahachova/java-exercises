package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.listener.OrderListener;

public interface CoffeeMachineController {
    void processOrder(String[] order);
    void addListener(OrderListener listener);
}
