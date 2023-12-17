package com.gateways.product.adapters.out.persistence;

import com.gateways.product.adapters.mapper.Mapper;
import com.gateways.product.adapters.out.persistence.model.PurchaseRequestModel;
import com.gateways.product.domain.pojos.ConvertedPurchaseResponse;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.pojos.PurchaseResponse;
import com.gateways.product.domain.ports.repository.PurchaseRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PurchaseRepositoryAdapter implements PurchaseRepositoryPort {

    private final SpringDataH2Repository h2Repository;

    public PurchaseRepositoryAdapter(SpringDataH2Repository h2Repository) {

        this.h2Repository = h2Repository;
    }

    @Override
    public PurchaseResponse savePurchaseRequest(PurchaseRequest purchaseRequest) {

        PurchaseRequestModel purchaseRequestModel = Mapper.fromDomainToModel(purchaseRequest);

        return Mapper.fromModelToDomain(h2Repository.save(purchaseRequestModel));

    }

    @Override
    public Optional<ConvertedPurchaseResponse> retrievePurchaseRequest(long purchaseId) {

        return Optional.ofNullable(Mapper.fromModelToDomainRetrieve(h2Repository.findById(purchaseId)));

    }
}
