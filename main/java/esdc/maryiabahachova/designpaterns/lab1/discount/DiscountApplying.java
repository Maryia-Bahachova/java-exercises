package esdc.maryiabahachova.designpaterns.lab1.discount;

public interface DiscountApplying {
    default double applyDiscount(Discount discountType, double total) {
        if (discountType == null) {
            System.out.println("You have no discount. Your total is $" + total);
            return total;
        } else {
            return total * discountType.getRate();
        }
    }
}