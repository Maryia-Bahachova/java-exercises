package lt.esdc.designpatterns.listener;

import lt.esdc.designpatterns.visitor.Order;

public class OrderHistoryListener implements OrderListener {

    private final OrderHistory history;

    public OrderHistoryListener(OrderHistory history) {
        this.history = history;
    }

    @Override
    public void onOrderProcessed(OrderEvent event) {
        history.addOrder(
                new Order(
                        event.getOrder(),
                        event.isSuccess(),
                        event.getPrice()
                )
        );
    }
}