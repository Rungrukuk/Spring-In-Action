// package tacos.kitchen.messaging.rabbit;

// import org.springframework.amqp.core.Message;
// import org.springframework.amqp.core.MessageProperties;
// import org.springframework.amqp.support.converter.MessageConverter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.lang.NonNull;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import tacos.domain.TacoOrder;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;
// import jakarta.transaction.Transactional;

// import java.io.IOException;

// public class TacoOrderMessageConverter implements MessageConverter {

// @PersistenceContext
// private EntityManager entityManager;
// @Autowired
// private ObjectMapper objectMapper;

// @Override
// @Transactional
// public @NonNull Message toMessage(@NonNull Object object, @NonNull
// MessageProperties messageProperties) {
// if (object instanceof TacoOrder) {
// TacoOrder tacoOrder = (TacoOrder) object;
// tacoOrder.getTacos().forEach(taco -> {
// taco.getIngredients().size();
// entityManager.detach(taco);
// });
// try {
// String json = objectMapper.writeValueAsString(tacoOrder);
// return new Message(json.getBytes(), messageProperties);
// } catch (JsonProcessingException e) {
// throw new RuntimeException("Error serializing TacoOrder to JSON", e);
// }
// } else {
// throw new IllegalArgumentException("Object is not an instance of TacoOrder");
// }
// }

// @Override
// @Transactional
// public @NonNull Object fromMessage(@NonNull Message message) {
// byte[] body = message.getBody();
// try {
// TacoOrder tacoOrder = objectMapper.readValue(body, TacoOrder.class);
// return tacoOrder;
// } catch (IOException e) {
// throw new RuntimeException("Error deserializing JSON to TacoOrder", e);
// }
// }
// }
