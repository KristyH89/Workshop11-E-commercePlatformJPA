package se.lexicon.dto;

// DTO used to expose address information safely
public record AddressResponseDto(
        String street,
        String city,
        String zipCode
) {}
