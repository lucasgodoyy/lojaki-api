package com.lucasgodoy.lojaki.domain.store.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a store (seller) in the system.
 *
 * This class belongs to the Domain layer and contains only business rules.
 * It does NOT depend on frameworks, persistence, or infrastructure.
 *
 * A Store can sell products from multiple brands.
 * Product listings are handled via StoreItem (separate concept).
 *
 * Relationships:
 * - 1 Store : N StoreItems
 */
public class Store {

    /**
     * Unique identifier of the store.
     */
    private final UUID id;

    /**
     * Store name. Required, 2-100 characters.
     */
    private String name;

    /**
     * Indicates whether the store is active.
     */
    private boolean active;

    /**
     * Soft delete timestamp. Null if store is not deleted.
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
    private Store(UUID id, String name) {
        validate(name);
        this.id = id;
        this.name = name;
        this.active = true;
        this.deletedAt = null;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====

    /**
     * Creates a new Store.
     *
     * @param name Store name
     * @return New Store instance
     */
    public static Store create(String name) {
        return new Store(UUID.randomUUID(), name);
    }

    // ===== Business Methods =====

    /**
     * Updates store name.
     */
    public void update(String name) {
        validate(name);
        this.name = name;
        this.updatedAt = Instant.now();
    }

    /**
     * Activates the store.
     */
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    /**
     * Deactivates the store.
     */
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }

    /**
     * Soft deletes the store.
     */
    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(String name) {
        if (name == null || name.trim().length() < 2 || name.trim().length() > 100) {
            throw new IllegalArgumentException("Store name is required and must be between 2 and 100 characters");
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
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}