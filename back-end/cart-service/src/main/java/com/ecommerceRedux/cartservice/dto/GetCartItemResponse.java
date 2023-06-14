package com.ecommerceRedux.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCartItemResponse {
    private String name; 

    private float price;

    private int quantity;

    private String img;

    private Long inventoryId;
}
