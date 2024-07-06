package tacos.config;

// import java.util.HashMap;
// import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
// import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
// import org.springframework.amqp.support.converter.SimpleMessageConverter;
// import org.springframework.amqp.support.converter.DefaultClassMapper;
// import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import tacos.domain.TacoOrder;
// import tacos.kitchen.messaging.OrderListener;
import tacos.kitchen.messaging.rabbit.TacoOrderMessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "my_queue";
    public static final String EXCHANGE_NAME = "my_exchange";
    public static final String ROUTING_KEY = "my_key";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new TacoOrderMessageConverter();
    }

    // @Bean
    // public DefaultClassMapper classMapper() {
    // DefaultClassMapper classMapper = new DefaultClassMapper();
    // Map<String, Class<?>> idClassMapping = new HashMap<>();
    // idClassMapping.put("tacoOrder", TacoOrder.class); // Adjust "tacoOrder" as
    // needed
    // classMapper.setIdClassMapping(idClassMapping);
    // return classMapper;
    // }

    // @Bean
    // public SimpleMessageListenerContainer listenerContainer(ConnectionFactory
    // connectionFactory,
    // MessageListenerAdapter listenerAdapter) {
    // SimpleMessageListenerContainer container = new
    // SimpleMessageListenerContainer();
    // container.setConnectionFactory(connectionFactory);
    // container.setQueueNames(QUEUE_NAME);
    // container.setMessageListener(listenerAdapter);
    // return container;
    // }

    // @Bean
    // public MessageListenerAdapter listenerAdapter(OrderListener listener) {
    // return new MessageListenerAdapter(listener, "receiveOrder");
    // }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
