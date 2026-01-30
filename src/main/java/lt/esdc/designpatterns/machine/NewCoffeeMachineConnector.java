package lt.esdc.designpatterns.machine;

import lt.esdc.designpatterns.exception.CoffeeMachineException;

import java.util.UUID;
import java.util.regex.Pattern;

public class NewCoffeeMachineConnector implements CoffeeMachineV75 {

    private static NewCoffeeMachineConnector instance;

    private static final Pattern ORDER_PATTERN = Pattern.compile(
            "(?i)^\\d+ml \\d+g \\d+ml(?:\\s+[a-z]+)*+$"
    );private String currentToken;
    private String currentSession;

    private NewCoffeeMachineConnector() {
    }

    public static NewCoffeeMachineConnector getInstance() {
        if (instance == null) {
            instance = new NewCoffeeMachineConnector();
        }
        return instance;
    }

    @Override
    public String getToken() {
        currentToken = UUID.randomUUID().toString().replace("-", "");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // восстановить флаг прерывания
            throw new CoffeeMachineException("Thread interrupted while generating token", e);
        }
        return currentToken;
    }

    @Override
    public String openSession(String token) {
        validateToken(token);
        currentSession = UUID.randomUUID().toString().replace("-", "");
        return currentSession;
    }

    @Override
    public void makeCoffee(String token, String session, String coffee) {
        validateToken(token);
        validateSession(session);
        validateOrder(coffee);

        System.out.println("Preparing coffee... " + coffee);
        System.out.println("..........");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CoffeeMachineException("Thread interrupted while generating token", e);
        }
        System.out.println("Ready");
    }

    @Override
    public void closeSession(String token, String session) {
        validateToken(token);
        validateSession(session);
        currentSession = null;
    }

    private void validateToken(String token) {
        if (token == null || !token.equals(currentToken)) {
            throw new IllegalArgumentException("Invalid or expired token.");
        }
    }

    private void validateSession(String session) {
        if (currentSession == null) {
            throw new IllegalStateException("No active session.");
        }
        if (!session.equals(currentSession)) {
            throw new IllegalArgumentException("Invalid session.");
        }
    }

    private void validateOrder(String order) {
        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }
        if (!ORDER_PATTERN.matcher(order).matches()) {
            throw new IllegalArgumentException(
                    "Invalid order format: '" + order +
                            "'. Expected format: <volume>ml <coffee>g <milk>ml [toppings...]"
            );
        }
    }
}