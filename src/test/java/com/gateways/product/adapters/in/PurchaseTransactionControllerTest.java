package com.gateways.product.adapters.in;

import com.gateways.product.adapters.mapper.Mapper;
import com.gateways.product.domain.pojos.PurchaseRequest;
import com.gateways.product.domain.usecases.PurchaseUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PurchaseTransactionControllerTest {

    @Mock
    private PurchaseUseCase purchaseUseCase;

    MockedStatic<Mapper> mapper;

    @InjectMocks
    private PurchaseTransactionController purchaseTransactionController;

    @BeforeEach
    void setUp() {
        mapper = mockStatic(Mapper.class);
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void unregister() {
        mapper.close();
    }

    @Test
    void givenRequestClass_whenCalledPurchaseMethod_thenReturnsResponseDTOSuccessfully() {

        PurchaseRequest request = PurchaseRequest
                .builder()
                .description("Test Description")
                .transactionDate(LocalDateTime.now())
                .purchaseAmount(BigDecimal.valueOf(100.00))
                .id(1L)
                .build();

        PurchaseResponseDTO responseDTO = PurchaseResponseDTO
                .builder()
                .description("Test Description")
                .transactionDate(LocalDateTime.now())
                .purchaseAmount(BigDecimal.valueOf(100.00))
                .id(1L)
                .build();


        mapper.when(() -> Mapper.fromDomainToDTO(purchaseUseCase.purchase(request))).thenReturn(responseDTO);

        verify(purchaseUseCase, times(1)).purchase(request);

        assertEquals(Mapper.fromDomainToDTO(purchaseUseCase.purchase(request)).getPurchaseAmount(), responseDTO.getPurchaseAmount());
        assertEquals(Mapper.fromDomainToDTO(purchaseUseCase.purchase(request)).getDescription(), responseDTO.getDescription());
        assertEquals(Mapper.fromDomainToDTO(purchaseUseCase.purchase(request)).getId(), responseDTO.getId());
        assertEquals(Mapper.fromDomainToDTO(purchaseUseCase.purchase(request)).getTransactionDate(), responseDTO.getTransactionDate());

    }

    @Test
    void givenInputDTO_whenMapped_thenReturnsMappedClassSuccessfully() {

        PurchaseRequestDTO requestDTO = new PurchaseRequestDTO();
        requestDTO.setDescription("Test Description");
        requestDTO.setTransactionDate(LocalDateTime.now());
        requestDTO.setPurchaseAmount(BigDecimal.valueOf(100.00));
        requestDTO.setId(1L);

       PurchaseRequest request = PurchaseRequest
                .builder()
                .description("Test Description")
                .transactionDate(LocalDateTime.now())
                .purchaseAmount(BigDecimal.valueOf(100.00))
                .id(1L)
                .build();

        mapper.when(() -> Mapper.fromDTOToDomain(requestDTO)).thenReturn(request);


        assertNotNull(requestDTO);
        assertEquals(Mapper.fromDTOToDomain(requestDTO).getPurchaseAmount(), request.getPurchaseAmount());
        assertEquals(Mapper.fromDTOToDomain(requestDTO).getDescription(), request.getDescription());
        assertEquals(Mapper.fromDTOToDomain(requestDTO).getId(), request.getId());
        assertEquals(Mapper.fromDTOToDomain(requestDTO).getTransactionDate(), request.getTransactionDate());


        mapper.verify(() -> Mapper.fromDTOToDomain(requestDTO), times(4));

    }

}
