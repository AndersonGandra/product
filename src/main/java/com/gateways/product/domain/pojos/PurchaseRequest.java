package com.gateways.product.domain.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class PurchaseRequest {

    private String description;

    private LocalDateTime transactionDate;

    private BigDecimal purchaseAmount;

    private long id;
}
