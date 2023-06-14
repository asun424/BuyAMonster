package com.ecommerceRedux.inventoryservice.service;

import com.ecommerceRedux.inventoryservice.model.InventoryItem;
import com.ecommerceRedux.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.ecommerceRedux.inventoryservice.specification.InventorySpecification.*;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public boolean enoughInventory(int quantity, Long productId){
        Specification<InventoryItem> specification = matchProductId(productId).and(greaterThanInvQuantity(quantity));
        InventoryItem inventoryItem = inventoryRepository.findOne(specification).orElse(null);
        return inventoryItem != null;
    }

}
