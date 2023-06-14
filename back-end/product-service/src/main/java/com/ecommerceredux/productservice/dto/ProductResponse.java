package com.ecommerceredux.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private List<String> common_locations;
    private List<String> drops;
    private Long inventoryId;
}
