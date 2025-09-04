package com.example.orderservice.dto;

import java.math.BigDecimal;

public record OrderLineItemRequestDto(String skuCode,
                                        BigDecimal price,
                                        Integer quantity) {
}
