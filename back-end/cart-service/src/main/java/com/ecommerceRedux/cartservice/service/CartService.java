package com.ecommerceRedux.cartservice.service;

import com.ecommerceRedux.cartservice.dto.CreateCartItemRequest;
import com.ecommerceRedux.cartservice.model.CartItem;
import com.ecommerceRedux.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerceRedux.cartservice.specification.CartSpecification.matchUserId;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;

    public List<CartItem> getCartItems(Long userId){
        //use custom specification as WHERE CLAUSE TO find all products with SAME user ID
        Specification<CartItem> specification = matchUserId(userId);
        List<CartItem> cartItems = cartRepository.findAll(specification);
        log.info("Found all items for userId: {}", userId);
        return cartItems;
    }
    public void createCartItem(CreateCartItemRequest createCartItemRequest){
        //send GET request to PRODUCT w/ quantity
        //put IF statement inside GET to check where call comes -> check request < inventory
        // if PASS, send back product info, if FAIL send error back
        //if get product info, check if it is in DB already?
        //NO create new object || YES update existing quantity
        CartItem newCartItem = new CartItem().builder()
                .productId(createCartItemRequest.getProductId())
                .quantity(createCartItemRequest.getQuantity())
                .userId(createCartItemRequest.getUserId())
                .build();

        cartRepository.save(newCartItem);
        log.info("Item {} has been saved", newCartItem.getId());
    }

    public void deleteCartItem(Long cartItemId){
        cartRepository.deleteById(cartItemId);
        log.info("Item {} has been deleted", cartItemId);
    }
}


