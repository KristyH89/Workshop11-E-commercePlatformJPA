package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.Customer;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/*
    Repository interface for managing Customer entities.
    Extends JpaRepository to provide CRUD operations and query methods.
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Finds a customer by their unique email address.
    Optional<Customer> findByEmail(String email);

    // Finds customers by last name, ignoring case sensitivity.
    List<Customer> findByLastNameIgnoreCase(String lastName);

    // Finds customers living in a specific city (via their Address).
    List<Customer> findByAddress_CityIgnoreCase(String city);

    // Searches for customers whose email contains the given keyword (case-insensitive).
    List<Customer> findByEmailContainingIgnoreCase(String keyword);

    // Finds customers created after the given timestamp.
    List<Customer> findByCreatedAtAfter(Instant date);

    // Finds customers created between two timestamps.
    List<Customer> findByCreatedAtBetween(Instant start, Instant end);

    // Counts how many customers live in a specific city.
    long countByAddress_CityIgnoreCase(String city);

    // Checks if a customer exists with the given email.
    boolean existsByEmail(String email);

}
