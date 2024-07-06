// package tacos.kitchen.messaging.jms;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jms.annotation.JmsListener;
// import org.springframework.stereotype.Component;

// import jakarta.jms.JMSException;
// import jakarta.jms.ObjectMessage;
// import lombok.extern.slf4j.Slf4j;
// import tacos.domain.TacoOrder;
// import tacos.kitchen.KitchenUI;

// @Component
// @Slf4j
// public class OrderListener {

// private KitchenUI kitchenUI;

// @Autowired
// public OrderListener(KitchenUI kitchenUI) {
// this.kitchenUI = kitchenUI;
// }

// @JmsListener(destination = "tacocloud.order.queue")
// public void receiveOrder(ObjectMessage message) {
// try {
// TacoOrder tacoOrder = (TacoOrder) message.getObject();
// kitchenUI.displayOrder(tacoOrder);
// } catch (JMSException | ClassCastException e) {
// log.error("Error processing JMS Object message", e);
// }
// }
// }
