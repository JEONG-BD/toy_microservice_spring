package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(
                        inventory -> new InventoryResponseDto(
                                inventory.getSkuCode(),
                                inventory.getQuantity() > 0))
                                .toList();
    }
}
