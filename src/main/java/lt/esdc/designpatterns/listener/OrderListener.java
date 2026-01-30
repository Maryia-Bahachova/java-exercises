package lt.esdc.designpatterns.listener;

public interface OrderListener {
    void onOrderProcessed(OrderEvent event);
}