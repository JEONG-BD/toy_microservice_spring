package com.example.orderservice.dto;

import java.util.List;

public record OrderCreateRequestDto(List<OrderLineItemRequestDto> items) {
}
