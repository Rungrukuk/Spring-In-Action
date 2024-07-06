package tacos.kitchen.messaging.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import tacos.domain.TacoOrder;
import tacos.kitchen.KitchenUI;

@Component
public class KafkaOrderListener {
    private KitchenUI ui;

    @Autowired
    public KafkaOrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(autoStartup = "${listen.auto.start:true}", topics = "tacocloud.orders.topic")
    public void receiveOrder(TacoOrder message) {
        ui.displayOrder(message);
    }

}
