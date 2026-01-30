package lt.esdc.designpatterns.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationListener implements OrderListener {

   private final Logger logger = LoggerFactory.getLogger(NotificationListener.class);
    @Override
    public void onOrderProcessed(OrderEvent event) {
        if (event.isSuccess()) {
            logger.info("✅ Order completed: {}", event.getOrder());
        } else {
            logger.info("❌ Order failed: {}", event.getOrder());
        }
    }
}