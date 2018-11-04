package com.currencycloud.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Currency {

    private String code;
    private Integer decimalPlaces;
    private String name;
    private Boolean onlineTrading;

    public String getCode() {
        return code;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public String getName() {
        return name;
    }

    public Boolean getOnlineTrading() {
        return onlineTrading;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"));
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("decimalPlaces", decimalPlaces);
        map.put("name", name);
        map.put("onlineTrading", onlineTrading);

        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return String.format("{\"error\": \"%s\"}", e.getMessage());
        }
    }
}
