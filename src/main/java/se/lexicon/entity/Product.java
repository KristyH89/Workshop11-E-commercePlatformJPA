package se.lexicon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the product
    @Column(nullable = false)
    private String name;

    /* List of image URLs stored in a separate table (product_images).
       Uses @ElementCollection because images are simple String values.  */

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    // Current price of the product.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /* Many products belong to one category.
       FetchType.LAZY is used to avoid unnecessaty loading of category data. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;

    /* Many-to-Many relationship with promotions.
       Product is the owning side and defines the join table.
       Cascade is intentionally avoided to prevent deleting shared promotions. */

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "products_promotions",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    @ToString.Exclude
    private Set<Promotion> promotions = new HashSet<>();

    /* Helper method to maintain both sides of the bidirectional Many-to-Many relationship.
       Ensures that when a promotion is added to this product, the product is also added
       to the promotion's product collection. This keeps the in-memory state consistent
       with the database relationship. */
    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
        promotion.getProducts().add(this);
    }

    /* Helper method to safely remove a promotion from this product.
       Also removes this product from the promotion's product collection to keep
       the bidirectional relationship consistent and avoid stale references. */
    public void removePromotion(Promotion promotion) {
        promotions.remove(promotion);
        promotion.getProducts().remove(this);
    }

}


