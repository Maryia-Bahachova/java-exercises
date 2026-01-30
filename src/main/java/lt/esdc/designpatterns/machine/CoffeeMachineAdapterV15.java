package lt.esdc.designpatterns.machine;

public class CoffeeMachineAdapterV15 implements CoffeeMachineClient {

    private final CoffeeMachineV15 machine;

    public CoffeeMachineAdapterV15(CoffeeMachineV15 machine) {
        this.machine = machine;
    }

    @Override
    public void prepare(String command) {
        machine.send(command);
    }
}