package se.lexicon.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(

        // Product name must not be empty
        @NotBlank(message = "Product name is required")
        @Size(min = 1, max = 150)
        String name,

        // Price must be positive
        @NotNull(message = "Price is required")
        @Min(value = 1, message = "Price must be at least 1")
        BigDecimal price,

        // Category ID must be provided to link the product to a category
        @NotNull(message = "Category ID is required")
        Long categoryId
) {}
