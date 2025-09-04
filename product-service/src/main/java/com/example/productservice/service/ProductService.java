package com.example.productservice.service;

import com.example.productservice.dto.ProductCreateRequestDTO;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductCreateRequestDTO dto){
        Product product = Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();

        productRepository.save(product);
        log.info("Product: {} is saved", product.getId());
    }

    public ProductResponseDto findProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없다"));
        return toResponseDto(product);
    }

    public List<ProductResponseDto> findProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toResponseDto)
                .toList();
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }
}
