package tacos.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.domain.TacoOrder;

import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class TacoOrderDeserializer implements Deserializer<TacoOrder> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public TacoOrder deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(data, TacoOrder.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to TacoOrder: " + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
    }
}
