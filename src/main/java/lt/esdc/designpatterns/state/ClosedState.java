package lt.esdc.designpatterns.state;

import lt.esdc.designpatterns.machine.CoffeeMachineV15;

public class ClosedState implements ConnectorState {
    private int ignoredCount = 0;

    @Override
    public void handle(String command, CoffeeMachineV15 connector, CoffeeMachineStateContext context) {
        ignoredCount++;
        if (ignoredCount >= 5) {
            context.setState(new SemiClosedState());
            ignoredCount = 0;
        }
    }
}