package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponseDto;
import com.example.orderservice.dto.OrderCreateRequestDto;
import com.example.orderservice.dto.OrderLineItemRequestDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderCreateRequestDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderDto.items()
                .stream().map(this::mapToDto).toList();

        order.addOrderLineItems(orderLineItemsList);
        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // call inventory-service
        InventoryResponseDto[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service:60003/api/inventory",
                        uriBuilder ->
                                uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();
        boolean allProsuctsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponseDto::isInStock);
        if (allProsuctsInStock){
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
