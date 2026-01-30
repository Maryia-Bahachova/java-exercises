package lt.esdc.designpatterns.proxy;

import lt.esdc.designpatterns.machine.CoffeeMachineV75;

public class CachedTokenCoffeeMachineProxy implements CoffeeMachineV75 {

    private final CoffeeMachineV75 connector;
    private String cachedToken;

    public CachedTokenCoffeeMachineProxy(CoffeeMachineV75 connector) {
        this.connector = connector;
    }

    @Override
    public String getToken() {
        if (cachedToken == null) {
            cachedToken = connector.getToken();
        }
        return cachedToken;
    }

    @Override
    public String openSession(String token) {
        return connector.openSession(token);
    }

    @Override
    public void makeCoffee(String token, String session, String coffee) {
        connector.makeCoffee(token, session, coffee);
    }

    @Override
    public void closeSession(String token, String session) {
        connector.closeSession(token, session);
    }
}