package com.lucasgodoy.lojaki.infrastructure.persistence.entity;

import com.lucasgodoy.lojaki.domain.user.model.Role;
import jakarta.persistence.*;
import java.util.UUID;

/**
 * JPA Entity representing a system user for persistence.
 *
 * This class is responsible for mapping the User domain model
 * to the database. It contains no business logic, only persistence-related annotations.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * Unique identifier of the user.
     * Automatically generated as UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Email of the user.
     * Unique constraint enforced at the database level.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Role of the user (ADMIN, CUSTOMER, STAFF).
     * Stored as a string in the database.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Indicates whether the user account is active.
     * Used to enable/disable login and operations.
     */
    @Column(nullable = false)
    private boolean active;

    /**
     * Default constructor required by JPA.
     * Should not be used directly.
     */
    protected UserEntity() {}

    /**
     * Constructor to create a UserEntity instance.
     *
     * @param id     Unique identifier
     * @param email  User email, must be unique
     * @param role   Role of the user
     * @param active Active status
     */
    public UserEntity(UUID id, String email, Role role, boolean active) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    // ===== Getters and Setters =====


    public UUID getId() { return id; }


    public void setId(UUID id) { this.id = id; }


    public String getEmail() { return email; }


    public void setEmail(String email) { this.email = email; }


    public Role getRole() { return role; }


    public void setRole(Role role) { this.role = role; }


    public boolean isActive() { return active; }


    public void setActive(boolean active) { this.active = active; }
}
