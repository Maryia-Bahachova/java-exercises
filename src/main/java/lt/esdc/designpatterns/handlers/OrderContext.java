package lt.esdc.designpatterns.handlers;

import lt.esdc.designpatterns.product.CoffeeDrink;
import java.util.ArrayList;
import java.util.List;

public class OrderContext {

    private final String input;
    private String coffeeType;
    private List<String> toppings = new ArrayList<>();
    private String discount;
    private CoffeeDrink coffee;
    private double finalPrice;

    public OrderContext(String input) {
        this.input = input;
    }

    public String getInput() { return input; }
    public String getCoffeeType() { return coffeeType; }
    public void setCoffeeType(String coffeeType) { this.coffeeType = coffeeType; }
    public List<String> getToppings() { return toppings; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }
    public String getDiscount() { return discount; }
    public void setDiscount(String discount) { this.discount = discount; }
    public CoffeeDrink getCoffee() { return coffee; }
    public void setCoffee(CoffeeDrink coffee) { this.coffee = coffee; }
    public double getFinalPrice() { return finalPrice; }
    public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }
}