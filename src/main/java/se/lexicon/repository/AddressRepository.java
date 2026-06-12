package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.Address;

import java.util.List;

/*
    Repository for managing Address entities.
    Provides query method based on Spring Data JPA conventions.
 */

public interface AddressRepository extends JpaRepository<Address, Long> {

    // Finds all Addresses with the exact zip code.
    List<Address> findByZipCode(String zipCode);

    // Finds addresses by city, ignoring case sensitivity.
    List<Address> findByCityIgnoreCase(String city);

    // Searches for addresses where the street contains the given text.
    List<Address> findByStreetContainingIgnoreCase(String street);

    // Finds addresses where the zip code starts with the given prefix.
    List<Address> findByZipCodeStartingWith(String prefix);

    // Counts how many addresses share the same zip code.
    Long countByZipCode(String zipCode);
}
