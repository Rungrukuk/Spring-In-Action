// package tacos.messaging;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jms.core.JmsTemplate;
// import org.springframework.jms.core. MessagePostProcessor;
// import org.springframework.lang.NonNull;
// import org.springframework.stereotype.Service;

// import lombok.extern.slf4j.Slf4j;
// import jakarta.jms.Destination;
// import jakarta.jms.JMSException;
// import jakarta.jms.Message;
// import tacos.domain.TacoOrder;

// @Service
// @Slf4j
// public class JmsOrderMessagingService implements OrderMessagingService {
// private final JmsTemplate jms;

// // private Destination orderQueue;

// @Autowired
// public JmsOrderMessagingService(JmsTemplate jms
// // , Destination orderQueue
// ) {
// this.jms = jms;
// // this.orderQueue = orderQueue;
// }

// @Override
// public void sendOrder(TacoOrder order) {
// jms.convertAndSend("tacocloud.order.queue", order,
// message -> {
// message.setStringProperty("X_ORDER_SOURCE", "WEB");
// return message;
// });

// }
// @Override
// public void sendOrder(TacoOrder order) {
// log.info("SEND ORDER: " + order);
// }

// }