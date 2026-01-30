package lt.esdc.designpatterns.machine;

public class CoffeeMachineAdapterV75 implements CoffeeMachineClient {

    private final CoffeeMachineV75 machine;

    public CoffeeMachineAdapterV75(CoffeeMachineV75 machine) {
        this.machine = machine;
    }

    @Override
    public void prepare(String order) {
        String token = machine.getToken();
        String session = machine.openSession(token);
        machine.makeCoffee(token, session, order);
        machine.closeSession(token, session);
    }
}