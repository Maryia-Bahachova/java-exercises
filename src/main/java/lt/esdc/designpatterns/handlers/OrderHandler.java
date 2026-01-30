package lt.esdc.designpatterns.handlers;

public abstract class OrderHandler {

    protected OrderHandler next;

    public OrderHandler linkWith(OrderHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(OrderContext context);

    protected void goNext(OrderContext context) {
        if (next != null) {
            next.handle(context);
        }
    }
}