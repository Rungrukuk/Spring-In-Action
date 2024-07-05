package tacos.kitchen.messaging.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;
import tacos.kitchen.KitchenUI;
import org.hibernate.Hibernate;

@Component
@Slf4j
public class OrderListener {

    private KitchenUI kitchenUI;

    @Autowired
    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                TacoOrder tacoOrder = (TacoOrder) objectMessage.getObject();

                for (Taco taco : tacoOrder.getTacos()) {
                    Hibernate.initialize(taco.getIngredients());
                }

                kitchenUI.displayOrder(tacoOrder);
            } else {
                log.warn("Received message of unsupported type: {}", message.getClass().getName());
            }
        } catch (JMSException | ClassCastException e) {
            log.error("Error processing JMS message", e);
        }
    }
}