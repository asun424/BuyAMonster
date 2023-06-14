package com.ecommerceRedux.orderservice.service;


import com.ecommerceRedux.orderservice.dto.OrderRequest;
import com.ecommerceRedux.orderservice.dto.SingleOrderItem;
import com.ecommerceRedux.orderservice.model.OrderDemographic;
import com.ecommerceRedux.orderservice.model.OrderItem;
import com.ecommerceRedux.orderservice.model.OrderStock;
import com.ecommerceRedux.orderservice.repository.OrderDemographicRepository;
import com.ecommerceRedux.orderservice.repository.OrderItemRepository;
import com.ecommerceRedux.orderservice.repository.OrderStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDemographicRepository orderDemographicRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderStockRepository orderStockRepository;

    public void createNewOrder(OrderRequest orderRequest){
        OrderDemographic orderDemographic = OrderDemographic.builder()
                .firstName(orderRequest.getFirstName())
                .lastName(orderRequest.getLastName())
                .location(orderRequest.getLocation())
                .paymentMethod(orderRequest.getPaymentMethod())
                .totalAmount(orderRequest.getTotalAmount())
                .userId(orderRequest.getUserId())
                .build();

        orderDemographicRepository.save(orderDemographic);

        List<SingleOrderItem> orderItems = orderRequest.getOrderItems();

        for(int i = 0; i < orderItems.size(); ++i){
            OrderItem orderItem = OrderItem.builder()
                    .productId(orderItems.get(i).getProductId())
                    .orderQuantity(orderItems.get(i).getQuantity())
                    .price(orderItems.get(i).getPrice())
                    .orderDemographicId(orderDemographic.getId())
                    .build();
            orderItemRepository.save(orderItem);

            OrderStock orderStock = OrderStock.builder()
                    .productId(orderItems.get(i).getProductId())
                    .unitPrice(orderItems.get(i).getPrice())
                    .totalAmtSold(orderItems.get(i).getQuantity())
                    .totalProductRevenue(orderItems.get(i).getPrice() * orderItems.get(i).getQuantity())
                    .build();
            orderStockRepository.save(orderStock);
        }





    }

}
