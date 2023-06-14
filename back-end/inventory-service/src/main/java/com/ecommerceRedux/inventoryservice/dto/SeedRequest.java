package com.ecommerceRedux.inventoryservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class SeedRequest {
    private String name;
    private List<String> common_locations;
    private String description;
    private List<String> drops;
    private BigDecimal price;
    private String image;
}
