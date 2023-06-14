package com.ecommerceRedux.cartservice.controller;


import com.ecommerceRedux.cartservice.dto.CreateCartItemRequest;
import com.ecommerceRedux.cartservice.model.CartItem;
import com.ecommerceRedux.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> getCartItems(@PathVariable("userId") Long userId){
        return cartService.getCartItems(userId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCartItem(@RequestBody CreateCartItemRequest createCartItemRequest){
        cartService.createCartItem(createCartItemRequest);
    }

    @DeleteMapping({"cartItemId"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@PathVariable("cartItemId") Long cartItemId){
        cartService.deleteCartItem(cartItemId);
    }
}
