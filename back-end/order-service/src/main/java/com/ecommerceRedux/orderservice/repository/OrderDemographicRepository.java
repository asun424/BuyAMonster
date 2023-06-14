package com.ecommerceRedux.orderservice.repository;

import com.ecommerceRedux.orderservice.model.OrderDemographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDemographicRepository extends JpaRepository<OrderDemographic, Long> {
}



