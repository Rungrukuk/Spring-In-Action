package tacos.kitchen.messaging.jms.converter;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.lang.NonNull;

import tacos.domain.TacoOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

public class TacoOrderMessageConverter implements MessageConverter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public @NonNull Message toMessage(
            @NonNull Object object, @NonNull Session session) throws JMSException, MessageConversionException {
        if (object instanceof TacoOrder) {
            TacoOrder tacoOrder = (TacoOrder) object;
            tacoOrder.getTacos().forEach(taco -> {
                taco.getIngredients().size();
                entityManager.detach(taco);
            });
            return session.createObjectMessage((Serializable) tacoOrder);
        } else {
            throw new MessageConversionException("Object is not an instance of TacoOrder");
        }
    }

    @Override
    public @NonNull Object fromMessage(@NonNull Message message) throws JMSException, MessageConversionException {
        if (message instanceof ObjectMessage) {
            return ((ObjectMessage) message).getObject();
        } else {
            throw new MessageConversionException("Message is not an ObjectMessage");
        }
    }
}
