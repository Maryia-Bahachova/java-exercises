package lt.esdc.designpatterns.state;

import lt.esdc.designpatterns.machine.CoffeeMachineV15;

public interface ConnectorState {
    void handle(String command, CoffeeMachineV15 connector, CoffeeMachineStateContext context);
}