package se.lexicon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequestDto(

        // First name must not be empty
        @NotBlank(message = "First name is required")
        @Size(min = 1, max = 100)
        String firstName,

        // Lastname must not be empty
        @NotBlank(message = "Last name is required")
        @Size(min = 1, max = 100)
        String lastName,

        // Email must be valid and not empty
        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is required")
        @Size(max = 250)
        String email,

        @NotBlank(message = "password should not be empty")
        @Size(min = 8, max = 30)
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.</?]).+$",
                message = "Password must contain at least one letter and one special character "
        )
        String password,

        // Address fields must not be empty
        @NotBlank(message = "Street is required")
        @Size(min = 1, max = 50)
        String street,

        @NotBlank(message = "City is required")
        @Size(min = 1, max = 100)
        String city,

        @NotBlank(message = "Zip code is required")
        @Size(max = 10)
        String zipCode
){}





