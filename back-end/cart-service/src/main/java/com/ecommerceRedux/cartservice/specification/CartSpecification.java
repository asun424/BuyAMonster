package com.ecommerceRedux.cartservice.specification;

import com.ecommerceRedux.cartservice.model.CartItem;
import org.springframework.data.jpa.domain.Specification;

public class CartSpecification {
    public static Specification<CartItem> matchUserId(Long userId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<Long>get("userId"), userId);
    }
}
