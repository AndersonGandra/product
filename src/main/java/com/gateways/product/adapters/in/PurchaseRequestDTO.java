package com.gateways.product.adapters.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class PurchaseRequestDTO {

    @NotNull
    @Size(max = 50, message = "Description field cannot have more than 50 characters.")
    @JsonProperty("description")
    private String description;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("transactionDate")
    private LocalDateTime transactionDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "purchaseAmount value should be more than 0.0")
    @Digits(integer=10, fraction=2, message = "purchaseAmount field cannot have more than 10 digits and 2 decimal places.")
    @JsonProperty("purchaseAmount")
    private BigDecimal purchaseAmount;

    @JsonProperty("id")
    private long id;

}
