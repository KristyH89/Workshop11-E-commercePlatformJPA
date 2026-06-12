package se.lexicon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quantity of the product purchased in this order.
    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    /* Price of the product at the time of purchase.
       Stored here to preserve historical pricing even if the product price changes later. */
    @Min(0)
    @Column(name = "price_at_purchase", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtPurchase;

    /* Many order items belong to one order.
       This is the owning side of the relationship (order_id). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    private Order order;

    /* Each order item references exactly one product.
       FetchType.LAZY avoids loading product details unless needed. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @ToString.Exclude
    private Product product;
}
