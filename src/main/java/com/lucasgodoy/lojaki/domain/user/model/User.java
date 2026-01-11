package com.lucasgodoy.lojaki.domain.user.model;

import java.util.UUID;

/**
 * Represents a system user in the platform.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * Shopify-like model:
 * - A User can own a Customer profile
 * - A User can have different roles (ADMIN, CUSTOMER, etc.)
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
    private String email;

    /**
     * User role within the system.
     * Defines permissions and access level.
     */
    private Role role;

    /**
     * Indicates whether the user account is active.
     */
    private boolean active;

    // ===== Protected Constructor =====
    /**
     * Protected constructor required by some frameworks
     * (e.g., JPA, serialization tools). Not intended for direct use.
     */
    protected User() {
        this.id = null;
        this.email = null;
        this.role = null;
    }

    // ===== Factory / Constructor =====
    /**
     * Creates a new User instance.
     *
     * @param id    Unique identifier
     * @param email Email address (mandatory)
     * @param role  Role of the user (mandatory)
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

    // ===== Business Methods =====
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

    // ===== Getters =====
    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public boolean isActive() { return active; }
}
