package tacos.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.domain.TacoOrder;

import org.apache.kafka.common.serialization.Serializer;
import java.util.Map;

public class TacoOrderSerializer implements Serializer<TacoOrder> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public byte[] serialize(String topic, TacoOrder data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing TacoOrder to JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        // Nothing to do
    }
}
