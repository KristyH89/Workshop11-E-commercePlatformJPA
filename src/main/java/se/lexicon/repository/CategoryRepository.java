package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find a category by its name (case-insensitive).
    Optional<Category> findByNameIgnoreCase(String name);

    // Check if a category exists by name.
    boolean existsByNameIgnoreCase(String name);

    // Optional: Find categories whose name contains a given keyword.
    List<Category> findByNameContainingIgnoreCase(String keyword);

    // Optional: Count how many categories exist.
    long count();

}
