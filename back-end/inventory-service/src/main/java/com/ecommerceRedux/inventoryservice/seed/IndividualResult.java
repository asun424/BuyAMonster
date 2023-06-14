package com.ecommerceRedux.inventoryservice.seed;

import lombok.Data;

import java.util.List;

@Data
public class IndividualResult {
    private String category;
    private List<String> common_locations;
    private String description;
    private List<String> drops;
    private Long id;
    private String image;
    private String name;
}
