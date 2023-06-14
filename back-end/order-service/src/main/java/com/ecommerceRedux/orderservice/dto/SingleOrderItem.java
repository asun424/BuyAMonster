package com.ecommerceRedux.orderservice.dto;

import lombok.Data;

@Data
public class SingleOrderItem {
    private Long productId;

    private int price;

    private int quantity;
}
