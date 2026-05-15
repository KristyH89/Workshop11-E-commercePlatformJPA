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

    Optional<Customer> findByEmail(String email);

    List<Customer> findByLastNameIgnoreCase(String LastName);

    List<Customer> findByAddress_CityIgnoreCase(String city);

    List<Customer> findByEmailContainingIgnoreCase(String keyword);

    List<Customer> findByCreatedAtAfter(Instant date);

    List<Customer> findByCreatedAtBetween(Instant start, Instant end);

    long countByAddress_CityIgnoreCase(String city);

    boolean existsByEmail(String email);

}
