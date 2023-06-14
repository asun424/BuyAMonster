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
public class ProductRequest {
    private String name;
    private List<String> common_locations;
    private String description;
    private List<String> drops;
    private BigDecimal price;
    private String image;
    private Long inventoryId;
}
