package lt.esdc.designpatterns.state;

import lt.esdc.designpatterns.machine.CoffeeMachineV15;

public class OpenState implements ConnectorState {
    private int exceptionCount = 0;

    @Override
    public void handle(String command, CoffeeMachineV15 connector, CoffeeMachineStateContext context) {
        try {
            connector.send(command);
        } catch (Exception e) {
            exceptionCount++;
            if (exceptionCount >= 2) {
                context.setState(new ClosedState());
                exceptionCount = 0;
            }
        }
    }
}