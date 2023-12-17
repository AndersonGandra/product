package com.gateways.product.domain.usecases;

import com.gateways.product.domain.pojos.ConvertedPurchaseResponse;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.pojos.PurchaseResponse;

import java.util.Optional;

public interface PurchaseUseCase {

    PurchaseResponse purchase(PurchaseRequest request);

    Optional<ConvertedPurchaseResponse> retrieveConvertedPurchase(long purchaseId, String country) throws RuntimeException;

}
