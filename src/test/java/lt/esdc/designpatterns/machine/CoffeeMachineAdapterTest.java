package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CoffeeMachineAdapterTest {

    @Test
    void prepare_shouldDelegateToV15Connector() {
        CoffeeMachineV15 connector = mock(CoffeeMachineV15.class);
        CoffeeMachineClient client = new CoffeeMachineV15Client(connector);

        client.prepare("50ml 18g 0ml");

        verify(connector).send("50ml 18g 0ml");
    }

    @Test
    void prepare_shouldFollowV75Protocol() {
        CoffeeMachineV75 connector = mock(CoffeeMachineV75.class);

        when(connector.getToken()).thenReturn("token");
        when(connector.openSession("token")).thenReturn("session");

        CoffeeMachineClient client =
                new CoffeeMachineV75Client(connector);

        client.prepare("50ml 18g 0ml");

        verify(connector).getToken();
        verify(connector).openSession("token");
        verify(connector).makeCoffee("token", "session", "50ml 18g 0ml");
        verify(connector).closeSession("token", "session");
    }
}