package se.lexicon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    // Primary key for the Customer entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    // Customer's email address, must be unique
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Timestamp set automatically when the entity is first persisted
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    // One-to-one relationship with UserProfile, cascades all operations
    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    // Automatically set createdAt before the entity is saved
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    // Keep both sides of the one-to-one relationship in sync
    public void setProfile(UserProfile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setCustomer(this);
        }
    }

}