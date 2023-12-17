package com.gateways.product.domain.pojos;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class PurchaseResponse {

    private String message;

    private long id;

    private String description;

    private LocalDateTime transactionDate;

    private BigDecimal purchaseAmount;

}
