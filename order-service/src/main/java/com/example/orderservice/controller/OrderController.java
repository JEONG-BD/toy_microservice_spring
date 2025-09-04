package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderCreateRequestDto;
import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "order", description = "api for order management")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "place order")
    public ResponseEntity<Void> placeOrder(@RequestBody OrderCreateRequestDto dto){
        orderService.placeOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();    }
}
