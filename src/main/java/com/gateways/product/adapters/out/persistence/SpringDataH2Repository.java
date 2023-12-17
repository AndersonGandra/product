package com.gateways.product.adapters.out.persistence;

import com.gateways.product.adapters.out.persistence.model.PurchaseRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataH2Repository extends JpaRepository<PurchaseRequestModel, Long> {
}
