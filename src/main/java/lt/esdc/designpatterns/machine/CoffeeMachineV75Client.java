package lt.esdc.designpatterns.machine;

public class CoffeeMachineV75Client implements CoffeeMachineClient {

    private final CoffeeMachineV75 machine;

    public CoffeeMachineV75Client(CoffeeMachineV75 machine) {
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