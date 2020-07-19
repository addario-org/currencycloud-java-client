package com.currencycloud.client.model;

import com.currencycloud.client.Utils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarginBalanceTopUp {

    private String accountId;
    private String currency;
    private BigDecimal amount;
    private BigDecimal transferredAmount;
    private Date transferCompletedAt;

    protected MarginBalanceTopUp() { }

    private MarginBalanceTopUp(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static MarginBalanceTopUp create() {
        return new MarginBalanceTopUp();
    }

    public static MarginBalanceTopUp create(String currency, BigDecimal amount) {
        return new MarginBalanceTopUp(currency, amount);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getTransferredAmount() {
        return transferredAmount;
    }

    public Date getTransferCompletedAt() {
        return transferCompletedAt;
    }

    @Override
    public String toString() {
        final ObjectMapper objectMapper = new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDateFormat(new SimpleDateFormat(Utils.dateFormat));

        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);
        map.put("currency", currency);
        map.put("transferredAmount", transferredAmount);
        map.put("transferCompletedAt", transferCompletedAt);

        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return String.format("{\"error\": \"%s\"}", e.getMessage());
        }
    }
}
