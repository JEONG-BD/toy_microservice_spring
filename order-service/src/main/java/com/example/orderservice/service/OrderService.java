package com.example.orderservice.service;

import com.example.orderservice.dto.OrderCreateRequestDto;
import com.example.orderservice.dto.OrderLineItemRequestDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderCreateRequestDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderDto.items()
                .stream().map(this::mapToDto).toList();

        order.addOrderLineItems(orderLineItemsList);
        orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemRequestDto item) {
        return new OrderLineItems(
                item.skuCode(),
                item.price(),
                item.quantity());
    }
}
