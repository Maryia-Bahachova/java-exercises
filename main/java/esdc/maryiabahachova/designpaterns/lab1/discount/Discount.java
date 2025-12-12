package esdc.maryiabahachova.designpaterns.lab1.discount;

public enum Discount {
    STUDENT(0.95), VIP(0.9);

    private final double rate;

    Discount(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}