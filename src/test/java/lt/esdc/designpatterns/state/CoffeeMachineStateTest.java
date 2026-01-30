package lt.esdc.designpatterns.state;

import lt.esdc.designpatterns.machine.CoffeeMachineV15;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CoffeeMachineStateTest {

    private CoffeeMachineV15 connector;
    private CoffeeMachineStateContext context;

    @BeforeEach
    void setUp() {
        connector = mock(CoffeeMachineV15.class);
        context = new CoffeeMachineStateContext(connector);
    }

    // -------------------- OpenState Tests --------------------
    @Test
    void testOpenState_successfulSendStaysOpen() {
        context.setState(new OpenState());

        context.prepare("espresso");
        context.prepare("latte");

        assertInstanceOf(OpenState.class, context.getState());
        verify(connector, times(2)).send(anyString());
    }

    @Test
    void testOpenState_twoExceptions_switchesToClosed() {
        context.setState(new OpenState());

        doThrow(new RuntimeException()).when(connector).send("fail");

        // первый сбой
        context.prepare("fail");
        assertInstanceOf(OpenState.class, context.getState());

        // второй сбой → должно перейти в ClosedState
        context.prepare("fail");
        assertInstanceOf(ClosedState.class, context.getState());
    }

    // -------------------- ClosedState Tests --------------------
    @Test
    void testClosedState_ignoresFiveCommandsThenSemiClosed() {
        ClosedState closed = new ClosedState();
        context.setState(closed);

        // первые 4 вызова должны оставаться ClosedState
        for (int i = 0; i < 4; i++) {
            context.prepare("any");
            assertInstanceOf(ClosedState.class, context.getState());
        }

        // 5-й вызов → должен перейти в SemiClosedState
        context.prepare("trigger");
        assertInstanceOf(SemiClosedState.class, context.getState());
    }

    // -------------------- SemiClosedState Tests --------------------
    @Test
    void testSemiClosedState_successfulSend_switchesToOpen() {
        SemiClosedState semi = new SemiClosedState();
        context.setState(semi);

        context.prepare("coffee");
        verify(connector).send("coffee");
        assertInstanceOf(OpenState.class, context.getState());
    }

    @Test
    void testSemiClosedState_failedSend_switchesToClosed() {
        SemiClosedState semi = new SemiClosedState();
        context.setState(semi);

        doThrow(new RuntimeException()).when(connector).send("fail");

        context.prepare("fail");
        assertInstanceOf(ClosedState.class, context.getState());
    }
}