package com.lucasgodoy.lojaki.domain.product.model;

import com.lucasgodoy.lojaki.domain.store.model.Store;
import com.lucasgodoy.lojaki.domain.exception.DomainException;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product category owned by a store.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * - Category belongs to ONE Store
 * - Products can reference a Category
 */
public class Category {

    /**
     * Unique identifier of the category.
     */
    private final UUID id;

    /**
     * Store that owns this category.
     */
    private final Store store;

    /**
     * Name of the category. Required, 2-100 characters.
     */
    private String name;

    /**
     * Indicates whether the category is active. Defaults to true.
     */
    private boolean active;

    /**
     * Soft delete timestamp. Null if category is not deleted.
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

    // ===== Private constructor =====
    private Category(UUID id, Store store, String name) {
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
     * Creates a new Category instance.
     *
     * @param store Store that owns the category
     * @param name  Category name (2-100 characters)
     * @return New Category instance
     */
    public static Category create(Store store, String name) {
        return new Category(UUID.randomUUID(), store, name);
    }

    // ===== Business Methods =====
    /**
     * Updates the category's name.
     *
     * @param name New category name
     */
    public void update(String name) {
        validate(this.store, name);
        this.name = name;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the category as active.
     */
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the category as inactive.
     */
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the category as deleted (soft delete).
     */
    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(Store store, String name) {
        if (store == null) {
            throw new DomainException("Store is required for Category");
        }
        if (name == null || name.trim().length() < 2 || name.trim().length() > 100) {
            throw new DomainException("Category name is required and must be between 2 and 100 characters");
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
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
