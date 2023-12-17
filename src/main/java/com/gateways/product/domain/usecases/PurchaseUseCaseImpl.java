package com.gateways.product.domain.usecases;

import com.gateways.product.adapters.out.rest.ExchangeDataDTO;
import com.gateways.product.adapters.out.rest.ExchangeResponseDTO;
import com.gateways.product.domain.pojos.ConvertedPurchaseResponse;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.pojos.PurchaseResponse;
import com.gateways.product.domain.ports.repository.PurchaseRepositoryPort;
import com.gateways.product.domain.ports.rest.PurchaseRestPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class PurchaseUseCaseImpl implements  PurchaseUseCase{

    @Autowired
    PurchaseRepositoryPort purchaseRepositoryPort;

    @Autowired
    PurchaseRestPort purchaseRestPort;

    @Override
    public PurchaseResponse purchase(PurchaseRequest request) {

        request.setTransactionDate(LocalDateTime.now());

        return purchaseRepositoryPort.savePurchaseRequest(request);
    }

    @Override
    public Optional<ConvertedPurchaseResponse> retrieveConvertedPurchase(long purchaseId, String country) throws RuntimeException { //TODO create more specific exception

        Optional<ConvertedPurchaseResponse> convertedPurchaseResponse = purchaseRepositoryPort.retrievePurchaseRequest(purchaseId);

        LocalDate transactionDateMinusSixMonths = convertedPurchaseResponse.get().getTransactionDate().toLocalDate().minusMonths(6);

        ExchangeResponseDTO responseDTO = retrieveExchangeData(country, transactionDateMinusSixMonths);

        if(responseDTO.getData().isEmpty()){
            throw new RuntimeException("Purchase cannot be converted to the target currency.");
        }

        ExchangeDataDTO latestExchangeData = getLatestExchangeData(responseDTO);

        return completeConvertedResponse(convertedPurchaseResponse, latestExchangeData.getExchangeRate());

    }

    private ExchangeResponseDTO retrieveExchangeData(String country, LocalDate transactionDateMinusSixMonths){

       ExchangeResponseDTO response = purchaseRestPort.retrieveExchangeData(country, transactionDateMinusSixMonths);

        return response;

    }

    private ExchangeDataDTO getLatestExchangeData(ExchangeResponseDTO responseDTO) {

            return responseDTO.getData().stream()
                    .filter(data -> data.getRecordDate()
                            .isEqual(responseDTO
                                    .getData()
                                    .stream()
                                    .map(ExchangeDataDTO::getRecordDate)
                                    .max(LocalDate::compareTo)
                                    .get()))
                    .findFirst()
                    .orElse(null);
    }

    private Optional<ConvertedPurchaseResponse> completeConvertedResponse(Optional<ConvertedPurchaseResponse> convertedPurchaseResponse, BigDecimal exchangeRate){

        BigDecimal convertedAmount = convertedPurchaseResponse.get().getPurchaseAmount().multiply(exchangeRate).setScale(2, RoundingMode.HALF_EVEN);

        convertedPurchaseResponse.get().setExchangeRate(exchangeRate);

        convertedPurchaseResponse.get().setConvertedAmount(convertedAmount);

        return convertedPurchaseResponse;
    }
}
