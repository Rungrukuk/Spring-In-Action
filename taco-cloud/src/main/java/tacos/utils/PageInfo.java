package tacos.utils;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PageInfo {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Long lastId;
    private String lastValue;
    private String direction;

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public static PageInfo decodePageInfo(String pageInfoStr) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(pageInfoStr);
            String json = new String(decodedBytes);
            return objectMapper.readValue(json, PageInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encodePageInfo(PageInfo pageInfo) {
        try {
            String json = objectMapper.writeValueAsString(pageInfo);
            return Base64.getEncoder().encodeToString(json.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
