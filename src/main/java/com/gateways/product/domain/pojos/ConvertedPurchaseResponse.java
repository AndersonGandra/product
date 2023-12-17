package com.gateways.product.domain.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ConvertedPurchaseResponse {

    private long id;

    private String description;

    private LocalDateTime transactionDate;

    private BigDecimal purchaseAmount;

    private BigDecimal exchangeRate;

    private BigDecimal convertedAmount;

}
