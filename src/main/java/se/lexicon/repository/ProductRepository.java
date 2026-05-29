package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by their category name.
    List<Product> findByCategory_NameIgnoreCase(String categoryName);

    // Find products within a specific price range.
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // Optional: Find products whose name contains a given keyword.
    List<Product> findByNameContainingIgnoreCase(String keyword);

    // Optional: Find products cheaper than a given price.
    List<Product> findByPriceLessThan(BigDecimal price);

    // Optional: Find products ordered by price ascending.
    List<Product> findAllByOrderByPriceAsc();

    // Optional: Find products ordered by price descending.
    List<Product> findAllByOrderByPriceDesc();

    // Optional: Count how many products exist in a specific category.
    long countByCategory_Id(Long categoryId);

    // Optional: Find products by category ID.
    List<Product> findByCategory_Id(Long categoryId);

}
