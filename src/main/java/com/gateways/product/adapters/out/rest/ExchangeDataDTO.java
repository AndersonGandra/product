package com.gateways.product.adapters.out.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeDataDTO {

    @JsonProperty("exchange_rate")
    private BigDecimal exchangeRate;

    @JsonProperty("record_date")
    private LocalDate recordDate;

}
