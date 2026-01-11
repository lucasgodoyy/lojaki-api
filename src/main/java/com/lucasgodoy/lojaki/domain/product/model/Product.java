package com.lucasgodoy.lojaki.domain.product.model;

import com.lucasgodoy.lojaki.domain.exception.DomainException;
import com.lucasgodoy.lojaki.domain.product.valueobject.Money;
import com.lucasgodoy.lojaki.domain.store.model.Brand;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product in the system.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * It does NOT depend on frameworks, persistence, or infrastructure.
 *
 * Relationships:
 * - 1 Product : 1 Inventory (not included here)
 * - Product references Category (optional)
 */
public class Product {

    /**
     * Unique identifier of the product.
     */
    private final UUID id;

    /**
     * Product name. Required, 3-200 characters.
     */
    private String name;

    /**
     * Product description. Optional.
     */
    private String description;

    /**
     * Product price as a value object.
     */
    private Money price;

    /**
     * Associated Brand. Can be null.
     */
    private Brand brand;

    /**
     * Associated Category. Can be null.
     */
    private Category category;

    /**
     * Product visibility status. Defaults to true.
     */
    private boolean active;

    /**
     * Soft delete timestamp. Null if product is not deleted.
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
    private Product(UUID id,
                    String name,
                    String description,
                    Money price,
                    Brand brand,
                    Category category) {
        validate(name, price);
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.active = true;
        this.deletedAt = null;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====
    /**
     * Creates a new Product instance.
     *
     * @param name Name of the product (3-100 characters)
     * @param description Optional description of the product
     * @param price Price of the product as a Money value object
     * @param category Optional associated category
     * @return New Product instance
     */
    public static Product create(String name,
                                 String description,
                                 Money price,
                                 Brand brand,
                                 Category category) {
        return new Product(UUID.randomUUID(), name, description, price, brand, category);
    }

    // ===== Business Methods =====
    /**
     * Updates product details. Price and category can also be updated.
     *
     * @param name New name (3-100 characters)
     * @param description New optional description
     * @param price New price as a Money value object
     * @param category New optional category
     */
    public void update(String name,
                       String description,
                       Money price,
                       Brand brand,
                       Category category) {
        validate(name, price);
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the product as active.
     */
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the product as inactive and triggers domain events externally.
     */
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
        // ProductDeactivatedEvent should be triggered in service layer
    }

    /**
     * Soft delete the product.
     */
    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(String name, Money price) {
        if (name == null || name.trim().length() < 3 || name.trim().length() > 100) {
            throw new DomainException("Product name is required and must be between 3 and 100 characters");
        }
        if (price == null) {
            throw new DomainException("Price is required");
        }
        if (price.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Price amount must be >= 0");
        }
        if (price.getCurrency() == null) {
            throw new DomainException("Currency is required");
        }
        if (brand == null) {
            throw new DomainException("Brand is required");
        }


    }

    // ===== Getters =====
    public UUID getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public Money getPrice() { return price; }

    public Brand getBrand() {
        return brand;
    }

    public Category getCategory() { return category; }

    public boolean isActive() { return active; }

    public Instant getDeletedAt() { return deletedAt; }

    public Instant getCreatedAt() { return createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }

    // ===== Equals and HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
