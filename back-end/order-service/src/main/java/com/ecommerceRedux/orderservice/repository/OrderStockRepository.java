package com.ecommerceRedux.orderservice.repository;

import com.ecommerceRedux.orderservice.model.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStockRepository extends JpaRepository<OrderStock, Long> {
}
