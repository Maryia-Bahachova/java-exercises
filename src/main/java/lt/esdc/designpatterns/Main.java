package lt.esdc.designpatterns;

import lt.esdc.designpatterns.facade.CoffeeShopFacade;
import lt.esdc.designpatterns.listener.*;
import lt.esdc.designpatterns.visitor.ReportVisitor;
import lt.esdc.designpatterns.visitor.StatisticsVisitor;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose region (Italy / Lithuania): ");
        String region = scanner.nextLine().trim().toLowerCase();

        System.out.println("Choose machine version (v15 / v75): ");
        String version = scanner.nextLine().trim().toLowerCase();

        OrderHistory history = new OrderHistory();

        CoffeeShopFacade shop = new CoffeeShopFacade(region, version);

        shop.addListener(new NotificationListener());
        shop.addListener(new OrderHistoryListener(history));

        while (true) {
            System.out.println("Order coffee (or type 'exit' to quit):");
            String order = scanner.nextLine().trim().toLowerCase();
            if ("exit".equals(order)) break;

            shop.processOrder(order);
        }

        System.out.println("\n=== Order statistics ===");

        StatisticsVisitor statisticsVisitor = new StatisticsVisitor();
        history.accept(statisticsVisitor);
        statisticsVisitor.print();

        System.out.println("\n=== Report ===");
        ReportVisitor reportVisitor = new ReportVisitor();
        history.accept(reportVisitor);
        System.out.println(reportVisitor.getReport());

        System.out.println("\nThank you for visiting!");
    }
}