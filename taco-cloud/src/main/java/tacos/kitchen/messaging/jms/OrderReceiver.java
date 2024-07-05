package tacos.kitchen.messaging.jms;

import tacos.domain.TacoOrder;

public interface OrderReceiver {
    TacoOrder receiveOrder();
}
