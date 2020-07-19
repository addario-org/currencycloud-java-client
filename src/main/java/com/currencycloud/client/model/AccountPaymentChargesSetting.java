package com.currencycloud.client.model;

import com.currencycloud.client.Utils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AccountPaymentChargesSetting {

    private String chargeSettingsId;
    private String accountId;
    private String chargeType;
    private boolean enabled;
    @JsonProperty("default")
    private boolean isDefault;

    protected AccountPaymentChargesSetting() { }

    private AccountPaymentChargesSetting(String accountId) {
        this.accountId = accountId;
    }

    private AccountPaymentChargesSetting(String accountId, String chargeSettingsId) {
        this.accountId = accountId;
        this.chargeSettingsId = chargeSettingsId;
    }

    private AccountPaymentChargesSetting(String accountId, String chargeSettingsId, boolean enabled, boolean isDefault) {
        this.accountId = accountId;
        this.chargeSettingsId = chargeSettingsId;
        this.enabled = enabled;
        this.isDefault = isDefault;
    }

    public static AccountPaymentChargesSetting create() {
        return new AccountPaymentChargesSetting();
    }

    public static AccountPaymentChargesSetting create(String accountId) {
        return new AccountPaymentChargesSetting(accountId);
    }

    public static AccountPaymentChargesSetting create(String accountId, String chargeSettingsId) {
        return new AccountPaymentChargesSetting(accountId, chargeSettingsId);
    }

    public static AccountPaymentChargesSetting create(String accountId, String chargeSettingsId, boolean enabled, boolean isDefault) {
        return new AccountPaymentChargesSetting(accountId, chargeSettingsId, enabled, isDefault);
    }

    public String getChargeSettingsId() {
        return chargeSettingsId;
    }

    public void setChargeSettingsId(String chargeSettingsId) {
        this.chargeSettingsId = chargeSettingsId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        final ObjectMapper objectMapper = new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDateFormat(new SimpleDateFormat(Utils.dateFormat));

        Map<String, Object> map = new HashMap<>();
        map.put("chargeSettingId", chargeSettingsId);
        map.put("accountId", accountId);
        map.put("chargeType", chargeType);
        map.put("enabled", enabled);
        map.put("default", isDefault);

        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return String.format("{\"error\": \"%s\"}", e.getMessage());
        }
    }
}
