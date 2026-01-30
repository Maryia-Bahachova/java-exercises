package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CoffeeMachineClientTest {

    private CoffeeMachineV15 v15Mock;
    private CoffeeMachineV75 v75Mock;

    @BeforeEach
    void setup() {
        v15Mock = mock(CoffeeMachineV15.class);
        v75Mock = mock(CoffeeMachineV75.class);
    }

    @Test
    void testCoffeeMachineV15Client_prepare_callsSend() {
        CoffeeMachineV15Client client = new CoffeeMachineV15Client(v15Mock);
        String order = "200ml 15g 100ml";

        client.prepare(order);

        verify(v15Mock, times(1)).send(order);
    }

    @Test
    void testCoffeeMachineV75Client_prepare_callsTokenSessionAndMakeCoffee() {
        CoffeeMachineV75Client client = new CoffeeMachineV75Client(v75Mock);
        String order = "250ml 15g 200ml";

        String fakeToken = "token123";
        String fakeSession = "session456";

        when(v75Mock.getToken()).thenReturn(fakeToken);
        when(v75Mock.openSession(fakeToken)).thenReturn(fakeSession);

        client.prepare(order);

        verify(v75Mock, times(1)).getToken();
        verify(v75Mock, times(1)).openSession(fakeToken);
        verify(v75Mock, times(1)).makeCoffee(fakeToken, fakeSession, order);
        verify(v75Mock, times(1)).closeSession(fakeToken, fakeSession);
    }

    @Test
    void testCoffeeMachineV75Client_prepare_throwsIfTokenFails() {
        CoffeeMachineV75Client client = new CoffeeMachineV75Client(v75Mock);
        when(v75Mock.getToken()).thenThrow(new RuntimeException("token error"));

        String order = "100ml 10g 50ml";

        try {
            client.prepare(order);
        } catch (RuntimeException e) {
            // expected
        }

        verify(v75Mock, times(1)).getToken();
        verifyNoMoreInteractions(v75Mock);
    }
}