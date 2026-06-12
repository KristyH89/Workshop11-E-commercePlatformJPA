package se.lexicon.dto;

import java.math.BigDecimal;

// DTO returned to the client when fetching product data
public record ProductResponseDto(

        // Database-generated ID
        Long id,

        // Product name
        String name,

        // Product price
        BigDecimal price,

        // Flattened category name for convenience
        String categoryName
) {}
