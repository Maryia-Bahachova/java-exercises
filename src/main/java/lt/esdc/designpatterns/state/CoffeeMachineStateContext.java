package lt.esdc.designpatterns.state;

import lt.esdc.designpatterns.machine.CoffeeMachineClient;
import lt.esdc.designpatterns.machine.CoffeeMachineV15;

public class CoffeeMachineStateContext implements CoffeeMachineClient {
    private ConnectorState state;
    private final CoffeeMachineV15 connector;

    public CoffeeMachineStateContext(CoffeeMachineV15 connector) {
        this.connector = connector;
        this.state = new OpenState();
    }

    public void setState(ConnectorState state) {
        this.state = state;
    }

    @Override
    public void prepare(String command) {
        state.handle(command, connector, this);
    }

    public ConnectorState getState() {
        return state;
    }
}