package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Optional: Find all order items belonging to a specific order.
    List<OrderItem> findByOrder_Id(Long orderId);

    // Optional: Find all order items for a specific product.
    List<OrderItem> findByProduct_Id(Long productId);

    // Optional: Find order items where quantity is greater than a given value.
    List<OrderItem> findByQuantityGreaterThan(Integer quantity);
}
