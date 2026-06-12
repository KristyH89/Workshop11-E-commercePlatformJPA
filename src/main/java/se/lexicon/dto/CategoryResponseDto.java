package se.lexicon.dto;

// DTO used to expose category information to the client
public record CategoryResponseDto(

        // Database-generated ID of the category
        Long id,

        // Category name
        String name
) {}
