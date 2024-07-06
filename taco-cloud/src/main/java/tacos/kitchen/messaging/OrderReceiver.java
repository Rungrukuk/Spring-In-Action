package tacos.kitchen.messaging;

import tacos.domain.TacoOrder;

public interface OrderReceiver {
    TacoOrder receiveOrder();
}
