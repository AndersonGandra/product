package com.gateways.product.domain.ports.repository;

import com.gateways.product.domain.pojos.ConvertedPurchaseResponse;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.pojos.PurchaseResponse;

import java.util.Optional;

public interface PurchaseRepositoryPort {

    PurchaseResponse savePurchaseRequest(PurchaseRequest purchaseRequest);

    Optional<ConvertedPurchaseResponse> retrievePurchaseRequest(long purchaseId);
}
