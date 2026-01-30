package lt.esdc.designpatterns.listener;

import lt.esdc.designpatterns.visitor.Order;
import lt.esdc.designpatterns.visitor.ReportVisitor;
import lt.esdc.designpatterns.visitor.StatisticsVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ListenerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testNotificationListenerSuccess() {
        NotificationListener listener = new NotificationListener();
        OrderEvent event = new OrderEvent("Order 1", true, 100.0);

        listener.onOrderProcessed(event);

        assertTrue(outContent.toString().contains("✅ Order completed: Order 1"));
    }

    @Test
    void testNotificationListenerFailure() {
        NotificationListener listener = new NotificationListener();
        OrderEvent event = new OrderEvent("Order 2", false, 50.0);

        listener.onOrderProcessed(event);

        assertTrue(outContent.toString().contains("❌ Order failed: Order 2"));
    }

    @Test
    void testOrderHistoryListener() {
        OrderHistory history = new OrderHistory();
        OrderHistoryListener listener = new OrderHistoryListener(history);
        OrderEvent event = new OrderEvent("Order 3", true, 75.0);

        listener.onOrderProcessed(event);

        assertEquals(1, historySize(history));
        assertEquals("Order 3", historyGetDescription(history));
    }

    // Вспомогательные методы для доступа к приватному списку (через reflection)
    private int historySize(OrderHistory history) {
        try {
            var field = OrderHistory.class.getDeclaredField("orders");
            field.setAccessible(true);
            return ((java.util.List<?>) field.get(history)).size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String historyGetDescription(OrderHistory history) {
        try {
            var field = OrderHistory.class.getDeclaredField("orders");
            field.setAccessible(true);

            Object value = field.get(history);

            if (!(value instanceof java.util.List<?> list)) {
                throw new IllegalStateException("orders is not a List");
            }

            Object element = list.getFirst();

            if (!(element instanceof Order order)) {
                throw new IllegalStateException("Element is not Order");
            }

            return order.getDescription();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testReportVisitor() {
        OrderHistory history = new OrderHistory();
        history.addOrder(new Order("Order A", true, 100));
        history.addOrder(new Order("Order B", false, 50));

        ReportVisitor visitor = new ReportVisitor();
        history.accept(visitor);

        String report = visitor.getReport();
        assertTrue(report.contains("✔ Order A"));
        assertTrue(report.contains("✘ Order B"));
    }

    @Test
    void testStatisticsVisitor() {
        OrderHistory history = new OrderHistory();
        history.addOrder(new Order("Order A", true, 100));
        history.addOrder(new Order("Order B", false, 50));
        history.addOrder(new Order("Order C", true, 200));

        StatisticsVisitor visitor = new StatisticsVisitor();
        history.accept(visitor);

        assertEquals(3, getField(visitor, "total"));
        assertEquals(2, getField(visitor, "success"));
        assertEquals(1, getField(visitor, "failed"));
        assertEquals(300.0, getField(visitor, "revenue"));
    }

    private Object getField(Object obj, String name) {
        try {
            var field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}