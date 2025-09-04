package com.example.productservice.dto;

import java.math.BigDecimal;

public record ProductCreateRequestDTO(String name, String description, BigDecimal price) {
}
