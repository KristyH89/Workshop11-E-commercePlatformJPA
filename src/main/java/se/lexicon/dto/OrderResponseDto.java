package se.lexicon.dto;

import se.lexicon.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

// DTO returned after placing or fetching an order
public record OrderResponseDto(

        // Order ID
        Long id,

        // Timestamp when the order was created
        Instant createdAt,

        // ID of the customer who placed the order
        Long customerId,

        // Current order status (e.g., NEW,PAID, CANCELLED)
        OrderStatus status,

        // Customer email or name (optional, but useful)
        String customerEmail,

        // List of items in the order
        List<OrderItemResponseDto> items

) {}
