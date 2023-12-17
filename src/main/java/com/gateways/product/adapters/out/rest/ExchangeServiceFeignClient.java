package com.gateways.product.adapters.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "exchangeService", url = "${feignClient.url}", path = "${feignClient.path}")
public interface ExchangeServiceFeignClient {

    @GetMapping("?fields=" + "{fields}" + "&" + "filter=" + "{filters}" )
    ExchangeResponseDTO getExchangeServiceData(@RequestParam String fields,
                                               @PathVariable String filters);

}
