package se.lexicon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the category must be unique.
    @Column(nullable = false, unique = true)
    private String name;

    /* Optional bidirectional relationship.
       A category can have many products, but the Product entity owns the relationship.
       No cascading is used because products should not be removed when a category is deleted.
     */

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    /* Helper method to maintain both sides of the bidirectional relationship.
       Ensures that when a product is added to the category, the category is also
       set on the product entity.  */
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    /* Helper method to safely remove a product from the category.
        Also clears the category reference on the product to keep the
        relationship consistent. */
    public void removeProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }

}



