package com.lucasgodoy.lojaki.domain.store.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a brand in the system.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * It does NOT depend on frameworks, persistence, or infrastructure.
 *
 */
public class Brand {

    /**
     * Unique identifier of the brand.
     */
    private final UUID id;

    /**
     * Name of the brand. Required, 2-100 characters.
     */
    private String name;

    /**
     * Indicates whether the brand is active.
     */
    private boolean active;

    /**
     * Soft delete timestamp. Null if brand is not deleted.
     */
    private Instant deletedAt;

    /**
     * Timestamp of creation.
     */
    private final Instant createdAt;

    /**
     * Timestamp of last update.
     */
    private Instant updatedAt;

    // ===== Private Constructor =====
    private Brand(UUID id, String name) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.active = true;
        this.deletedAt = null;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====
    public static Brand create(String name) {
        return new Brand(UUID.randomUUID(), name);
    }

    // ===== Business Methods =====
    public void update(String name) {
        validateName(name);
        this.name = name;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }

    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validateName(String name) {
        if (name == null || name.trim().length() < 2 || name.trim().length() > 100) {
            throw new IllegalArgumentException("Brand name must be between 2 and 100 characters");
        }
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    public Instant getDeletedAt() { return deletedAt; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // ===== Equals and HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
