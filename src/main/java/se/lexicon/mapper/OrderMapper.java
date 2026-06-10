package se.lexicon.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.dto.OrderItemRequestDto;
import se.lexicon.dto.OrderItemResponseDto;
import se.lexicon.dto.OrderRequestDto;
import se.lexicon.dto.OrderResponseDto;
import se.lexicon.entity.*;

import java.util.List;

@Component
public class OrderMapper {

    // REQUEST -> ENTITY (Order)
    public Order toEntity(OrderRequestDto request, Customer customer, List<Product> products) {
        if (request == null) return null;

        Order order = new Order();
        order.setStatus(OrderStatus.CREATED); // Status is an enum
        order.setCustomer(customer);

        // Convert each OrderItemRequestDto into an OrderItem entity
        List<OrderItem> items = request.items().stream()
                .map(itemRequest -> toOrderItemEntity(itemRequest, products))
                .toList();

        // Attach items to the order (maintain bidirectional relationship)
        items.forEach(order::addItem);

        return order;
    }

    // Helper: REQUEST ITEM -> ENTITY ITEM
    private OrderItem toOrderItemEntity(OrderItemRequestDto request, List<Product> products) {

    if (request == null) return null;

    // Find the matching product
        Product product = products.stream()
                .filter(p-> p.getId().equals(request.productId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found: "+ request.productId()));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(request.quantity());
        item.setPriceAtPurchase(product.getPrice()); // snapshot of price

        return item;
    }

    // ENTITY -> RESPONSE (Order)
    public OrderResponseDto toResponse(Order order) {
        if (order == null) return null;

        return new OrderResponseDto(
                order.getId(),
                order.getOrderDate(),
                order.getCustomer().getId(),
                order.getStatus(),
                order.getCustomer().getEmail(),
                order.getItems().stream()
                        .map(this::toOrderItemResponse)
                        .toList()
                );
    }

    // Helper: ENTITY ITEM -> RESPONSE ITEM
    private OrderItemResponseDto toOrderItemResponse(OrderItem item) {
        if (item == null) return null;

        return new OrderItemResponseDto(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPriceAtPurchase()
        );
    }


}
