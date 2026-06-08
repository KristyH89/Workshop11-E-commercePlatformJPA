package se.lexicon.dto;

// DTO used to expose address information safely
public record AddressResponse(
        String street,
        String city,
        String zipCode
) {}
