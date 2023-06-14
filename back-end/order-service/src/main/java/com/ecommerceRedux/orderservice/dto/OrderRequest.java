package com.ecommerceRedux.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String firstName;

    private String lastName;

    private String location;

    private String paymentMethod;

    private int totalAmount;

    private Long userId;

    private List<SingleOrderItem> orderItems;


}
