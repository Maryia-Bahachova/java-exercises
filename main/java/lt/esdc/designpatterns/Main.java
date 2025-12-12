package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.CoffeeMachineController;
import lt.esdc.designpatterns.controller.CoffeeMachineControllerImpl;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.factory.ItalianCoffeeFactory;
import lt.esdc.designpatterns.factory.LithuanianCoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineConnector;
import lt.esdc.designpatterns.machine.CoffeeMachineV15;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(CoffeeMachineControllerImpl.class);
        CoffeeMachineV15 machine = CoffeeMachineConnector.getInstance();

        Scanner scanner = new Scanner(System.in);
        logger.info("Choose region (Italy / Lithuania): ");
        String region = scanner.nextLine().trim().toLowerCase();
        logger.info("Okay, what can I get for you? We have espresso, latte and cappuccino? ");
        String[] order = scanner.nextLine().trim().split(" ");


        CoffeeFactory factory;

        if (region.equals("italy")) {
            factory = new ItalianCoffeeFactory();
        } else {
            factory = new LithuanianCoffeeFactory();
        }

        CoffeeMachineController controller = new CoffeeMachineControllerImpl(machine, factory);
        controller.processOrder(order);
    }
}