package lt.esdc.designpatterns.listener;

public class OrderEvent {

    private final String order;
    private final boolean success;
    private final double price;

    public OrderEvent(String order, boolean success, double price) {
        this.order = order;
        this.success = success;
        this.price = price;
    }

    public String getOrder() { return order; }
    public boolean isSuccess() { return success; }
    public double getPrice() { return price; }
}