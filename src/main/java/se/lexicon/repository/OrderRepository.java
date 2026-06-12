package se.lexicon.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.Order;
import se.lexicon.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find all orders belonging to a specific customer.
    List<Order> findByCustomer_Id(Long customerId);

    // Find orders by status while avoiding the N+1 problem.
    // EntityGraph fetches order items together with orders to avoid extra queries.
    @EntityGraph(attributePaths = "items")
    List<Order> findByStatus(OrderStatus status);

    // Optional: Find orders created after a specific date.
    List<Order> findByOrderDateAfter(Instant date);

    // Optional: Find orders created between two dates.
    List<Order> findByOrderDateBetween(Instant start, Instant end);

    // Optional: Find orders that contain a specific product.
    List<Order> findByItems_Product_Id(Long productId);

    // Optional: Count orders by status.
    long countByStatus(OrderStatus status);

    // Optional: Find orders by customer ID and status.
    List<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status);
}
