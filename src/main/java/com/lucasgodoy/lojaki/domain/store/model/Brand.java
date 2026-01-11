package com.lucasgodoy.lojaki.domain.store.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a brand owned by a store.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * Shopify-like model:
 * - Brand belongs to ONE Store
 * - Products can reference a Brand
 */
public class Brand {

    /**
     * Unique identifier of the brand.
     */
    private final UUID id;

    /**
     * Store that owns this brand.
     */
    private final Store store;

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
    private Brand(UUID id, Store store, String name) {
        validate(store, name);
        this.id = id;
        this.store = store;
        this.name = name;
        this.active = true;
        this.deletedAt = null;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====
    /**
     * Creates a new Brand instance.
     *
     * @param store Store that owns the brand
     * @param name  Brand name (2-100 characters)
     * @return New Brand instance
     */
    public static Brand create(Store store, String name) {
        return new Brand(UUID.randomUUID(), store, name);
    }

    // ===== Business Methods =====
    /**
     * Updates the brand name.
     *
     * @param name New brand name
     */
    public void update(String name) {
        validate(this.store, name);
        this.name = name;
        this.updatedAt = Instant.now();
    }

    /**
     * Activates the brand.
     */
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    /**
     * Deactivates the brand.
     */
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }

    /**
     * Soft deletes the brand.
     */
    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(Store store, String name) {
        if (store == null) {
            throw new IllegalArgumentException("Store is required for Brand");
        }
        if (name == null || name.trim().length() < 2 || name.trim().length() > 100) {
            throw new IllegalArgumentException("Brand name must be between 2 and 100 characters");
        }
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Store getStore() { return store; }
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
