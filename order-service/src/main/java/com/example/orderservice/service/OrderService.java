package com.example.orderservice.service;

import com.example.orderservice.dto.OrderCreateRequestDto;
import com.example.orderservice.dto.OrderLineItemRequestDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderCreateRequestDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderDto.items()
                .stream().map(this::mapToDto).toList();

        order.addOrderLineItems(orderLineItemsList);

        // call inventory-service
        Boolean result = webClient.get()
                .uri("http://inventory-service:6002/api/inventory")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result){
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock please try again later");
        }



    }

    private OrderLineItems mapToDto(OrderLineItemRequestDto item) {
        return new OrderLineItems(
                item.skuCode(),
                item.price(),
                item.quantity());
    }
}
