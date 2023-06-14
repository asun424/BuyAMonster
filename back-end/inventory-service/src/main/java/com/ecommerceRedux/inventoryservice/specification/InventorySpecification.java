package com.ecommerceRedux.inventoryservice.specification;

import com.ecommerceRedux.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.domain.Specification;

public class InventorySpecification {
    public static Specification<InventoryItem> matchProductId(Long productId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<Long>get("productId"), productId);
    }

    public static Specification<InventoryItem> greaterThanInvQuantity(int reqQuantity){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.<Long>get("invQuantity"), reqQuantity);
    }
}
