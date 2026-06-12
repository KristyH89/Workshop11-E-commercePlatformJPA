package se.lexicon.dto;

import java.math.BigDecimal;

// DTO representing a single item in an order response
public record OrderItemResponseDto(

        // ID of the purchased product
        Long productId,

        // Product name for readability
        String productName,

        // Quantity ordered
        Integer quantity,

        // Price at the moment of purchase
        BigDecimal priceAtPurchase
) {}
