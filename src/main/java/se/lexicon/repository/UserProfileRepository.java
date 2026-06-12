package se.lexicon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.entity.UserProfile;

import java.util.List;
import java.util.Optional;

/*
    Repository for managing UserProfile entities.
    Provides query methods based on Spring Data JPA conventions.
 */

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // Finds a user profile by exact nickname.
    Optional<UserProfile> findByNickname(String nickname);

    // Searches for profiles where the phone number contains the given partial string.
    List<UserProfile> findByPhoneNumberContaining(String partial);

    // Returns all profiles that have a non-null bio.
    List<UserProfile> findByBioIsNotNull();

    // Finds profiles where the nickname starts with the given prefix (case-sensitive)
    List<UserProfile> findByNicknameStartingWith(String prefix);

    // Counts how many profiles have a phone number starting with the given prefix.
    long countByPhoneNumberStartingWith(String prefix);
}
