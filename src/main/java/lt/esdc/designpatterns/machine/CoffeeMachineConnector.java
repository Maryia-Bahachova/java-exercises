package lt.esdc.designpatterns.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class CoffeeMachineConnector implements CoffeeMachineV15 {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineConnector.class);
    private static CoffeeMachineConnector instance;
    private static final Pattern ORDER_PATTERN = Pattern.compile(
            "(?i)^\\d+ml \\d+g \\d+ml(?:\\s+[a-z]+)*+$"
    );
    private CoffeeMachineConnector() {
    }

    public static CoffeeMachineConnector getInstance() {
        if (instance == null) {
            instance = new CoffeeMachineConnector();
        }
        return instance;
    }

    @Override
    public void send(String order) {
        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }

        if (!ORDER_PATTERN.matcher(order).matches()) {
            throw new IllegalArgumentException(
                    "Invalid order format: " + order +
                            ". Expected format: <volume>ml <coffee>g <milk>ml [toppings...]"
            );
        }

        logger.info("Preparing coffee: {}", order);
        logger.info("..........");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Ready");
    }
}