package se.lexicon.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

// DTO used when placing a new order
public record OrderRequestDto(

        // Customer ID placing the order
        @NotNull(message = "Customer ID is required")
        Long customerId,

        // List of order items must not be empty
        @NotEmpty(message = "Order must contain at least one item")
        List<OrderItemRequestDto> items
) {}
