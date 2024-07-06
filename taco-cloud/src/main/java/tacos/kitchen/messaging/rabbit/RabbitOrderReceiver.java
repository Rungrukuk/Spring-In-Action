// package tacos.kitchen.messaging.rabbit;

// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.support.converter.MessageConverter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import lombok.extern.slf4j.Slf4j;
// import tacos.config.RabbitMQConfig;
// import tacos.domain.TacoOrder;
// import tacos.kitchen.KitchenUI;
// import tacos.kitchen.messaging.OrderReceiver;

// @Component
// @Slf4j
// public class RabbitOrderReceiver implements OrderReceiver {
// private RabbitTemplate rabbit;
// private MessageConverter converter;
// private KitchenUI kitchenUI;

// @Autowired
// public RabbitOrderReceiver(RabbitTemplate rabbit, MessageConverter converter,
// KitchenUI kitchenUI) {
// this.rabbit = rabbit;
// this.converter = converter;
// this.kitchenUI = kitchenUI;
// }

// public TacoOrder receiveOrder() {
// log.info("Receiving message from RabbitMQ");

// Message message = rabbit.receive(RabbitMQConfig.QUEUE_NAME);
// if (message != null) {
// TacoOrder receivedOrder = (TacoOrder) converter.fromMessage(message);
// kitchenUI.displayOrder(receivedOrder);
// return receivedOrder;
// } else {
// log.info("No message received from RabbitMQ");
// kitchenUI.displayOrder(null);
// return null;
// }
// }
// }
