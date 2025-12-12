package lt.esdc.designpatterns.product;

public class Coffee {
    private final int water;
    private final int amountOfCoffee;
    private final int milk;

    private Coffee(Builder builder) {
        this.water = builder.water;
        this.amountOfCoffee = builder.coffee;
        this.milk = builder.milk;
    }

    public String prepareCommand() {
        return String.format("%dml %dg %dml", water, amountOfCoffee, milk);
    }

    public static class Builder {
        private int water;
        private int coffee;
        private int milk;

        public Builder water(int water) {
            this.water = water;
            return this;
        }

        public Builder coffee(int coffee) {
            this.coffee = coffee;
            return this;
        }

        public Builder milk(int milk) {
            this.milk = milk;
            return this;
        }

        public Coffee build() {
            return new Coffee(this);
        }
    }
}