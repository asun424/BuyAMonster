package com.ecommerceredux.productservice.controller;

import com.ecommerceredux.productservice.dto.ProductRequest;
import com.ecommerceredux.productservice.dto.ProductResponse;
import com.ecommerceredux.productservice.dto.UpdateRequest;
import com.ecommerceredux.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getSingleProduct(@PathVariable("productId") String productId){
        return productService.getSingleProduct(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable("productId") String productId, @RequestBody UpdateRequest updateRequest){
        productService.updateProduct(productId, updateRequest);
    }

}
