package tacos.kitchen.messaging;

// import org.springframework.amqp.core.Message;

public interface OrderListener {
    public void receiveOrder(Object message);
}
