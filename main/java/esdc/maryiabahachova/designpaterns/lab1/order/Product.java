package esdc.maryiabahachova.designpaterns.lab1.order;

public class Product {
    private double price;
    private String type;

    public Product(double price, String type) {
        this.price = price;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}