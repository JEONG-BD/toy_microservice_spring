package com.example.inventoryservice.dto;

public record InventoryResponseDto(String skuCode,
                                   boolean isInStock) {
}
