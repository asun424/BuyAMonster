package com.ecommerceRedux.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartItemRequest {
    private Long productId;

    private Long userId;

    private int quantity;
}
