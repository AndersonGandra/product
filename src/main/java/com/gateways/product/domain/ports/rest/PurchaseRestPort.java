package com.gateways.product.domain.ports.rest;

import com.gateways.product.adapters.out.rest.ExchangeResponseDTO;

import java.time.LocalDate;

public interface PurchaseRestPort {
    ExchangeResponseDTO retrieveExchangeData(String country, LocalDate transactionDate);
}
