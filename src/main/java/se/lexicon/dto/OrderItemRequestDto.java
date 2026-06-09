package se.lexicon.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO representing a single item in an order request
public record OrderItemRequestDto(

        // Product ID must be provided
        @NotNull(message = "Product ID is required")
        Long productId,

        // Quantity must be at least 1
        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity
) {}
