package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
// import org.springframework.jms.core. MessagePostProcessor;
// import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.jms.Destination;
// import jakarta.jms.JMSException;
// import jakarta.jms.Message;
import tacos.domain.TacoOrder;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;

    private Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms,
            Destination orderQueue) {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jms.convertAndSend(orderQueue, order,
                message -> {
                    message.setStringProperty("X_ORDER_SOURCE", "WEB");
                    return message;
                });

    }

}