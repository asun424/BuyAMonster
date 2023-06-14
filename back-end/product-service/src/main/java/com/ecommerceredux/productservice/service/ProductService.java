package com.ecommerceredux.productservice.service;

import com.ecommerceredux.productservice.dto.ProductRequest;
import com.ecommerceredux.productservice.dto.ProductResponse;
import com.ecommerceredux.productservice.dto.UpdateRequest;
import com.ecommerceredux.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ecommerceredux.productservice.model.Product;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse getSingleProduct(String productId){
        Product singleProduct = productRepository.findById(productId).orElse(null);
        ProductResponse productResponse = ProductResponse.builder()
                .id(singleProduct.getId())
                .name(singleProduct.getName())
                .description(singleProduct.getDescription())
                .price(singleProduct.getPrice())
                .image(singleProduct.getImage())
                .inventoryId(singleProduct.getInventoryId())
                .common_locations(singleProduct.getCommon_locations())
                .drops(singleProduct.getDrops())
                .build();
        return productResponse;
    }
    public String createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .image(productRequest.getImage())
                .inventoryId(productRequest.getInventoryId())
                .common_locations(productRequest.getCommon_locations())
                .drops(productRequest.getDrops())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return product.getId();
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this:: mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .inventoryId((product.getInventoryId()))
                .build();
    }

    public void updateProduct(String productId, UpdateRequest updateRequest){
        Product productToUpdate = productRepository.findById(productId).orElse(null);
        productToUpdate.setInventoryId(updateRequest.getInventoryId());
        productRepository.save(productToUpdate);
        log.info("UPDATED PRODUCT");
    }
}
