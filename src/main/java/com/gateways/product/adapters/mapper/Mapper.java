package com.gateways.product.adapters.mapper;

import com.gateways.product.adapters.in.PurchaseRequestDTO;
import com.gateways.product.adapters.in.PurchaseResponseDTO;
import com.gateways.product.adapters.out.persistence.model.PurchaseRequestModel;
import com.gateways.product.domain.pojos.ConvertedPurchaseResponse;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.pojos.PurchaseResponse;

import java.util.Optional;

public class Mapper {

    public static PurchaseResponseDTO fromDomainToDTO(PurchaseResponse purchaseResponse){

        return PurchaseResponseDTO.builder()
                .id(purchaseResponse.getId())
                .description(purchaseResponse.getDescription())
                .purchaseAmount(purchaseResponse.getPurchaseAmount())
                .transactionDate(purchaseResponse.getTransactionDate())
                .build();

    }

    public static PurchaseRequest fromDTOToDomain(PurchaseRequestDTO purchaseRequestDTO){

        return PurchaseRequest.builder()
                .description(purchaseRequestDTO.getDescription())
                .purchaseAmount(purchaseRequestDTO.getPurchaseAmount())
                .build();

    }

    public static PurchaseRequestModel fromDomainToModel(PurchaseRequest request){

        return PurchaseRequestModel.builder()
                .id(request.getId())
                .description(request.getDescription())
                .purchaseAmount(request.getPurchaseAmount())
                .transactionDate(request.getTransactionDate())
                .build();

    }

    public static PurchaseResponse fromModelToDomain(PurchaseRequestModel requestModel){

        return PurchaseResponse.builder()
                .id(requestModel.getId())
                .description(requestModel.getDescription())
                .transactionDate(requestModel.getTransactionDate())
                .purchaseAmount(requestModel.getPurchaseAmount())
                .build();
    }

    public static ConvertedPurchaseResponse fromModelToDomainRetrieve(Optional<PurchaseRequestModel> purchaseRequestModel){

        return ConvertedPurchaseResponse.builder()
                .id(purchaseRequestModel.get().getId())
                .description(purchaseRequestModel.get().getDescription())
                .transactionDate(purchaseRequestModel.get().getTransactionDate())
                .purchaseAmount(purchaseRequestModel.get().getPurchaseAmount())
                .build();
    }

}
