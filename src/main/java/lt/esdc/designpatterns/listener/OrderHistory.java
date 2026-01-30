package lt.esdc.designpatterns.listener;


import lt.esdc.designpatterns.visitor.Order;
import lt.esdc.designpatterns.visitor.OrderVisitor;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {

    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void accept(OrderVisitor visitor) {
        for (Order o : orders) {
            o.accept(visitor);
        }
    }
}