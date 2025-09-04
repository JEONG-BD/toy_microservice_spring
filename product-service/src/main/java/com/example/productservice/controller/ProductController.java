package com.example.productservice.controller;

import com.example.productservice.dto.ProductCreateRequestDTO;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "API for product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "register a product")
    public void createProduct(@RequestBody ProductCreateRequestDTO dto){
        productService.createProduct(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find a product")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable String id){
        ProductResponseDto result = productService.findProduct(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/")
    @Operation(summary = "all product")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> result = productService.findProducts();
        return ResponseEntity.ok().body(result);
    }
}
