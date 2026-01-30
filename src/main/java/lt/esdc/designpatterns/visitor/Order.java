package lt.esdc.designpatterns.visitor;

public class Order {

    private final String description;
    private final boolean success;
    private final double price;

    public Order(String description, boolean success, double price) {
        this.description = description;
        this.success = success;
        this.price = price;
    }

    public boolean isSuccess() { return success; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    public void accept(OrderVisitor visitor) {
        visitor.visit(this);
    }
}