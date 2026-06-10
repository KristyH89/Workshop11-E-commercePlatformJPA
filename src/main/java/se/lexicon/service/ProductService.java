package se.lexicon.service;

import se.lexicon.dto.ProductRequestDto;
import se.lexicon.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    // Create a new product
    ProductResponseDto create(ProductRequestDto request);

    // FInd a product by ID
    ProductResponseDto findById(Long id);

    // Return all products
    List<ProductResponseDto> findAll();

    // Search products by name
    List<ProductResponseDto> searchByName(String name);

    // Update an existing product
    ProductResponseDto update(Long id, ProductRequestDto request);

    // Delete a product by ID
    void delete(Long id);
}
