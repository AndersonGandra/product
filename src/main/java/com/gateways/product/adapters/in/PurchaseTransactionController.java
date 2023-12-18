package com.gateways.product.adapters.in;

import com.gateways.product.adapters.mapper.Mapper;
import com.gateways.product.domain.pojos.ConvertedPurchaseResponse;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.usecases.PurchaseUseCase;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class PurchaseTransactionController {

    private final PurchaseUseCase purchaseUseCase;

    public PurchaseTransactionController(PurchaseUseCase purchaseUseCase) {

        this.purchaseUseCase = purchaseUseCase;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponseDTO> purchase(@RequestBody @NotNull @Valid PurchaseRequestDTO purchaseRequestDTO){

        PurchaseRequest request = Mapper.fromDTOToDomain(purchaseRequestDTO);

        PurchaseResponseDTO response = Mapper.fromDomainToDTO(purchaseUseCase.purchase(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/converted")
    public ResponseEntity<Optional<ConvertedPurchaseResponse>> retrieveConvertedPurchase(@RequestParam long purchaseId, @RequestParam String country){

        Optional<ConvertedPurchaseResponse> response = purchaseUseCase.retrieveConvertedPurchase(purchaseId, country);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
