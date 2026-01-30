package lt.esdc.designpatterns.machine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class CoffeeMachineConnectorTest {

    @Test
    void testSingleton() {
        CoffeeMachineConnector c1 = CoffeeMachineConnector.getInstance();
        CoffeeMachineConnector c2 = CoffeeMachineConnector.getInstance();

        assertSame(c1, c2);
    }

    @Test
    void testSend_validOrder() {
        CoffeeMachineConnector connector = CoffeeMachineConnector.getInstance();

        assertDoesNotThrow(() -> connector.send("100ml 10g 50ml"));
    }

    @Test
    void testSend_invalidOrder() {
        CoffeeMachineConnector connector = CoffeeMachineConnector.getInstance();

        assertThrows(IllegalArgumentException.class, () -> connector.send("invalid order"));
        assertThrows(IllegalArgumentException.class, () -> connector.send(""));
        assertThrows(IllegalArgumentException.class, () -> connector.send(null));
    }
}