package com.lucasgodoy.lojaki.domain.model;

import java.util.UUID;

/**
 * Represents a system user.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * It does NOT depend on frameworks, persistence, or infrastructure.
 */
public class User {

    /**
     * Unique identifier of the user.
     */
    private final UUID id;

    /**
     * User email address.
     * Used as the main identification credential.
     */
    private final String email;

    /**
     * User role within the system.
     * Defines permissions and access level.
     */
    private final Role role;

    /**
     * Indicates whether the user account is active.
     */
    private boolean active;

    /**
     * Protected constructor required by some frameworks
     * (e.g. JPA, serialization tools).
     *
     * Not intended for direct use.
     */
    protected User() {
        this.id = null;
        this.email = null;
        this.role = null;
    }

    /**
     * Creates a valid user instance.
     *
     * Business rules:
     * - Email is mandatory
     * - Role is mandatory
     * - User starts as active by default
     *
     * @param id    unique identifier
     * @param email user email
     * @param role  user role
     */
    public User(UUID id, String email, Role role) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (role == null) {
            throw new IllegalArgumentException("Role is required");
        }

        this.id = id;
        this.email = email;
        this.role = role;
        this.active = true;
    }

    /**
     * Deactivates the user account.
     */
    public void deactivate() {
        this.active = false;
    }

    /**
     * Reactivates the user account.
     */
    public void activate() {
        this.active = true;
    }

    /**
     * Checks if the user has administrator privileges.
     *
     * @return true if the user is an admin
     */
    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    // Getters

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}
