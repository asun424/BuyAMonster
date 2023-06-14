package com.ecommerceRedux.apigateway.repository;


import com.ecommerceRedux.apigateway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
