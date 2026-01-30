package lt.esdc.designpatterns.state;

import lt.esdc.designpatterns.machine.CoffeeMachineV15;

public class SemiClosedState implements ConnectorState {

    @Override
    public void handle(String command, CoffeeMachineV15 connector, CoffeeMachineStateContext context) {
        try {
            connector.send(command);
            context.setState(new OpenState());
        } catch (Exception e) {
            context.setState(new ClosedState());
        }
    }
}