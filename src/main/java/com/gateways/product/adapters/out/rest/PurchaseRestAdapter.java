package com.gateways.product.adapters.out.rest;

import com.gateways.product.domain.ports.rest.PurchaseRestPort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Component
public class PurchaseRestAdapter implements PurchaseRestPort {

    final ExchangeServiceFeignClient feignClient;

    public PurchaseRestAdapter(ExchangeServiceFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    final String FIELDS = "exchange_rate,record_date";

    @Override
    public ExchangeResponseDTO retrieveExchangeData(@PathVariable String country, @PathVariable LocalDate transactionDate) {

        String filters = "country:in:(" + country + "),record_date:gte:" + transactionDate;

        return feignClient.getExchangeServiceData(FIELDS, filters);
    }
}
