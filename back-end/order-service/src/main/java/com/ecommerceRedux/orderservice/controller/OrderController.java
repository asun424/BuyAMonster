package com.ecommerceRedux.orderservice.controller;


import com.ecommerceRedux.orderservice.dto.OrderRequest;
import com.ecommerceRedux.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createNewOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createNewOrder(orderRequest);
    }
}

