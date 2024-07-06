package tacos.messaging;

// import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
// import org.springframework.amqp.core.MessageProperties;
// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.config.RabbitMQConfig;
import tacos.domain.TacoOrder;

@Service
@Slf4j
public class RabbitOrderMessagingService
        implements OrderMessagingService {
    private RabbitTemplate rabbit;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    // public void sendOrder(TacoOrder order) {
    // MessageConverter converter = rabbit.getMessageConverter();
    // MessageProperties props = new MessageProperties();
    // props.setHeader("X_ORDER_SOURCE", "WEB");
    // Message message = converter.toMessage(order, props);
    // rabbit.send("tacocloud.order", message);
    // }
    // public void sendOrder(TacoOrder order) {
    // rabbit.convertAndSend("tacocloud.order", order);
    // }

    public void sendOrder(TacoOrder order) {
        rabbit.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, order,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) {
                        message.getMessageProperties().getHeaders().put("X_ORDER_SOURCE", "WEB");
                        return message;
                    }
                });
        log.info("Sent order: " + order);
    }

}