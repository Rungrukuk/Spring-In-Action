package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import tacos.config.RabbitMQConfig;
import tacos.domain.TacoOrder;
import tacos.kitchen.KitchenUI;
import tacos.kitchen.messaging.OrderListener;

@Component
@Slf4j
public class RabbitOrderListener implements OrderListener {
    private MessageConverter converter;
    private KitchenUI kitchenUI;

    @Autowired
    public RabbitOrderListener(MessageConverter converter, KitchenUI kitchenUI) {
        this.converter = converter;
        this.kitchenUI = kitchenUI;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveOrder(Message message) {
        log.info("Received message from RabbitMQ");

        TacoOrder receivedOrder = (TacoOrder) converter.fromMessage(message);
        kitchenUI.displayOrder(receivedOrder);
    }
}
