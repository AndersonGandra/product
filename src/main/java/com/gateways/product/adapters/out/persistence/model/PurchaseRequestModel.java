package com.gateways.product.adapters.out.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase")
public class PurchaseRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "transactionDate")
    private LocalDateTime transactionDate;

    @Column(name = "purchaseAmount")
    private BigDecimal purchaseAmount;

}
