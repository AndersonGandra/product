package com.gateways.product.adapters.out.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
//@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponseDTO {

    @JsonProperty("data")
    private List<ExchangeDataDTO> data;

}
