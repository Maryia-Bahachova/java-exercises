package lt.esdc.designpatterns.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatisticsVisitor implements OrderVisitor {

    private int total;
    private int success;
    private int failed;
    private double revenue;
    private static final Logger logger = LoggerFactory.getLogger(StatisticsVisitor.class);

    @Override
    public void visit(Order order) {
        total++;
        if (order.isSuccess()) {
            success++;
            revenue += order.getPrice();
        } else {
            failed++;
        }
    }

    public void print() {
        logger.info("Orders: {}", total);
        logger.info("Successful: {}", success);
        logger.info("Failed: {}", failed);
        logger.info("Revenue: {}", revenue);
        logger.info("Avg price: {}", success == 0 ? 0 : revenue / success);
    }
}