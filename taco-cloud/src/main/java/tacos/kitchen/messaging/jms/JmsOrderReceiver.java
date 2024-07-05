// package tacos.kitchen.messaging.jms;

// import jakarta.jms.JMSException;
// import jakarta.jms.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jms.core.JmsTemplate;
// import org.springframework.jms.support.converter.MessageConversionException;
// import org.springframework.jms.support.converter.MessageConverter;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.support.TransactionTemplate;

// import tacos.domain.TacoOrder;

// @Component
// public class JmsOrderReceiver implements OrderReceiver {

// private final JmsTemplate jms;
// private final MessageConverter converter;
// private final TransactionTemplate transactionTemplate;

// @Autowired
// public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter,
// TransactionTemplate transactionTemplate) {
// this.jms = jms;
// this.converter = converter;
// this.transactionTemplate = transactionTemplate;
// }

// @Override
// public TacoOrder receiveOrder() {
// return transactionTemplate.execute(status -> {
// Message message = jms.receive("tacocloud.order.queue");
// if (message != null) {
// TacoOrder order;
// try {
// order = (TacoOrder) converter.fromMessage(message);
// order.getTacos().forEach(taco -> taco.getIngredients().size());
// return order;
// } catch (MessageConversionException | JMSException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// // Initialize the collection here
// }
// return null;
// });
// }
// }
