package com.ecommerceRedux.inventoryservice.controller;


import com.ecommerceRedux.inventoryservice.dto.GetInventoryRequest;
import com.ecommerceRedux.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean enoughInventory(@RequestBody GetInventoryRequest inventoryRequest, @PathVariable("productId") Long productId){
        return inventoryService.enoughInventory(inventoryRequest.getQuantity(), productId);
    }

}
