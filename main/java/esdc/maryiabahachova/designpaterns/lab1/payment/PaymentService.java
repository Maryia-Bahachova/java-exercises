package esdc.maryiabahachova.designpaterns.lab1.payment;

public class PaymentService {
    public void charge(PaymentType paymentType, double total) {
        System.out.println("Charging with " + paymentType + " for $" + total);
    }
}