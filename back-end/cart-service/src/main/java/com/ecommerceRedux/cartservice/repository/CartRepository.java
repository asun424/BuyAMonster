package com.ecommerceRedux.cartservice.repository;

import com.ecommerceRedux.cartservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem> {

}
