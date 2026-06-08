package se.lexicon.dto;

// DTO returned to the client after creating or fetching a customer
public record CustomerResponse(

        // Database-generated ID
        Long id,

        // Combined first and last name for convenience
        String fullName,

        // Customer email
        String email,

        // Nested DTO for address information
        AddressResponse addressResponse
) {}
