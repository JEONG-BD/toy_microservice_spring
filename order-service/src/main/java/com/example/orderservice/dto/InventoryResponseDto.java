package com.example.orderservice.dto;

public record InventoryResponseDto(String skuCode,
                                   boolean isInStock) {
}
