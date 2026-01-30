package lt.esdc.designpatterns.machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewCoffeeMachineConnectorTest {

    private NewCoffeeMachineConnector connector;

    @BeforeEach
    void setUp() {
        connector = NewCoffeeMachineConnector.getInstance();
    }

    @Test
    void testSingleton() {
        NewCoffeeMachineConnector c1 = NewCoffeeMachineConnector.getInstance();
        NewCoffeeMachineConnector c2 = NewCoffeeMachineConnector.getInstance();
        assertSame(c1, c2);
    }

    @Test
    void testFullWorkflow() {
        String token = connector.getToken();
        String session = connector.openSession(token);

        assertDoesNotThrow(() -> connector.makeCoffee(token, session, "200ml 15g 100ml caramel"));
        assertDoesNotThrow(() -> connector.closeSession(token, session));
    }

    @Test
    void testInvalidToken() {
        String token = connector.getToken();
        String session = connector.openSession(token);

        assertThrows(IllegalArgumentException.class, () -> connector.makeCoffee("wrong", session, "100ml 10g 50ml"));
    }
}