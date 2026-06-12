package se.lexicon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique promotion code.
    @Column(nullable = false, unique = true)
    private String code;

    // Date when the promotion becomes active.
    private LocalDate startDate;

    // Date when the promotion expires. Can be null for open-ended promotions.
    private LocalDate endDate;

    /* Bidirectional Many-to-Many relationship.
       Product is the owning side; Promotion is the inverse side.
       No cascading is used to avoid accidental deletion of shared promotions. */
    @ManyToMany(mappedBy = "promotions")
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        if (product == null) return;

        this.products.add(product);
        product.getPromotions().add(this);
    }

    public void removeProduct(Product product) {
        if (product == null) return;

        this.products.remove(product);
        product.getPromotions().remove(this);
    }

}
