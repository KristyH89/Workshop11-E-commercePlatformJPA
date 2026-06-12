package se.lexicon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.dto.ProductRequestDto;
import se.lexicon.dto.ProductResponseDto;
import se.lexicon.service.ProductService;

import java.util.List;

/**
 *  Rest controller for managing products.
 *  Exposes endpoints under /api/v1/products.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Creates a new product.
     *
     * @param request the product data to create
     * @return the created product with HTTP 201 Created
     */

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @Valid @RequestBody ProductRequestDto request) {
        ProductResponseDto response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves all products.
     *
     * @return list of all products with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    /**
     * Searches products by name.
     *
     * @param name the name (or partial name) to search for
     * @return list of matching products with HTTP 200 OK
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchByName(
            @RequestParam String name ) {
        List<ProductResponseDto> results = productService.searchByName(name);
        return ResponseEntity.ok(results);
    }

    /**
     * Retrieves a single product by its ID.
     *
     * @param id the ID of the product
     * @return the found product with HTTP 200 OK
     * @throws se.lexicon.exception.ResourceNotFoundException if no product exists with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        ProductResponseDto response = productService.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing product
     *
     * @param id the ID of the product to update
     * @param request the updated product data
     * @return the updated product with HTTP 200 OK
     * @throws se.lexicon.exception.ResourceNotFoundException if no product exists with the given ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto request) {
        ProductResponseDto updated = productService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return HTTP 204 No Content on success
     * @throws se.lexicon.exception.ResourceNotFoundException if no product exists with the given ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
