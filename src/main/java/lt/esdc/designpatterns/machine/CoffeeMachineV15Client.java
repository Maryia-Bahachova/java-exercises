package lt.esdc.designpatterns.machine;

public class CoffeeMachineV15Client implements CoffeeMachineClient {

    private final CoffeeMachineV15 machine;

    public CoffeeMachineV15Client(CoffeeMachineV15 machine) {
        this.machine = machine;
    }

    @Override
    public void prepare(String order) {
        machine.send(order);
    }
}