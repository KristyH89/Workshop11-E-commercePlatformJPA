package se.lexicon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Timestamp when the order was created.
    private Instant orderDate;

    // Current status of the order, stored as a readable string.
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /* Each order belongs to exactly one customer.
       FetchType.LAZY prevents loading customer data unless needed. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private Customer customer;

    /* One order contains one or more order items.
       CascadeType.ALL ensures items are saved/updated/deleted with the order.
       orphanRemoval = true removes items that are no longer referenced.  */
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<OrderItem> items = new ArrayList<>();

    /* Helper method to maintain both sides of the bidirectional relationship.
       Ensures the business rule: an order must contain at least one item. */
    public void  addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

    /* Helper method to safely remove an item from the order
       while keeping both sides of the relationship synchronized. */
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }

    // Automatically set the order creation timestamp before persisting.
    @PrePersist
    public void prePersist() {
        orderDate = Instant.now();
    }

}
