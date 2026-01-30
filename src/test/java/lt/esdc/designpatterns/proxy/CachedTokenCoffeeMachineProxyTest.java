package lt.esdc.designpatterns.proxy;

import lt.esdc.designpatterns.machine.CoffeeMachineV75;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CachedTokenCoffeeMachineProxyTest {

    @Test
    void getToken_shouldBeCached() {
        CoffeeMachineV75 realConnector = mock(CoffeeMachineV75.class);
        when(realConnector.getToken()).thenReturn("TOKEN-123");

        CachedTokenCoffeeMachineProxy proxy =
                new CachedTokenCoffeeMachineProxy(realConnector);

        String firstCall = proxy.getToken();
        String secondCall = proxy.getToken();

        assertEquals("TOKEN-123", firstCall);
        assertEquals("TOKEN-123", secondCall);

        verify(realConnector, times(1)).getToken();
    }

    @Test
    void openSession_shouldDelegateToRealConnector() {
        CoffeeMachineV75 realConnector = mock(CoffeeMachineV75.class);
        CachedTokenCoffeeMachineProxy proxy =
                new CachedTokenCoffeeMachineProxy(realConnector);

        proxy.openSession("token");

        verify(realConnector).openSession("token");
    }

    @Test
    void makeCoffee_shouldDelegateToRealConnector() {
        CoffeeMachineV75 realConnector = mock(CoffeeMachineV75.class);
        CachedTokenCoffeeMachineProxy proxy =
                new CachedTokenCoffeeMachineProxy(realConnector);

        proxy.makeCoffee("token", "session", "coffee");

        verify(realConnector).makeCoffee("token", "session", "coffee");
    }

    @Test
    void closeSession_shouldDelegateToRealConnector() {
        CoffeeMachineV75 realConnector = mock(CoffeeMachineV75.class);
        CachedTokenCoffeeMachineProxy proxy =
                new CachedTokenCoffeeMachineProxy(realConnector);

        proxy.closeSession("token", "session");

        verify(realConnector).closeSession("token", "session");
    }
}