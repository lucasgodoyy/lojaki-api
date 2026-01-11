package com.lucasgodoy.lojaki.domain.product.model;

import com.lucasgodoy.lojaki.domain.exception.DomainException;
import com.lucasgodoy.lojaki.domain.product.valueobject.Money;
import com.lucasgodoy.lojaki.domain.store.model.Brand;
import com.lucasgodoy.lojaki.domain.store.model.Store;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product owned by a store.
 *
 * Domain layer only: contains business rules and invariants.
 * No persistence or framework dependencies.
 *
 * - Product belongs to ONE Store
 * - Product belongs to ONE Brand
 * - Product belongs to ONE Category (mandatory)
 * - Price and stock live inside Product
 */
public class Product {

    /** Unique identifier of the product */
    private final UUID id;

    /** Store that owns this product */
    private final Store store;

    /** Product name. Required. */
    private String name;

    /** Product description. Optional. */
    private String description;

    /** Product price as a value object */
    private Money price;

    /** Available stock quantity */
    private int stock;

    /** Associated Brand. Required. */
    private Brand brand;

    /** Associated Category. Required */
    private Category category;

    /** Product visibility status */
    private boolean active;

    /** Soft delete timestamp */
    private Instant deletedAt;

    /** Timestamp of creation */
    private final Instant createdAt;

    /** Timestamp of last update */
    private Instant updatedAt;

    // ===== Private Constructor =====
    private Product(UUID id,
                    Store store,
                    String name,
                    String description,
                    Money price,
                    int stock,
                    Brand brand,
                    Category category) {

        validate(store, name, price, stock, brand, category);

        this.id = id;
        this.store = store;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.active = true;
        this.deletedAt = null;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====
    /**
     * Creates a new Product instance
     */
    public static Product create(Store store,
                                 String name,
                                 String description,
                                 Money price,
                                 int stock,
                                 Brand brand,
                                 Category category) {

        return new Product(
                UUID.randomUUID(),
                store,
                name,
                description,
                price,
                stock,
                brand,
                category
        );
    }

    // ===== Business Methods =====

    /**
     * Updates product details
     */
    public void update(String name,
                       String description,
                       Money price,
                       int stock,
                       Brand brand,
                       Category category) {

        validate(this.store, name, price, stock, brand, category);

        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.updatedAt = Instant.now();
    }

    /** Decreases stock after a sale */
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new DomainException("Quantity must be greater than zero");
        }
        if (this.stock < quantity) {
            throw new DomainException("Insufficient stock");
        }
        this.stock -= quantity;
        this.updatedAt = Instant.now();
    }

    /** Marks the product as active */
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    /** Marks the product as inactive */
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }

    /** Soft delete the product */
    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(Store store,
                          String name,
                          Money price,
                          int stock,
                          Brand brand,
                          Category category) {

        if (store == null) throw new DomainException("Store is required");

        if (name == null || name.trim().length() < 3 || name.trim().length() > 100)
            throw new DomainException("Product name must be between 3 and 100 characters");

        if (price == null) throw new DomainException("Price is required");
        if (price.getAmount().compareTo(BigDecimal.ZERO) < 0) throw new DomainException("Price amount must be >= 0");
        if (price.getCurrency() == null) throw new DomainException("Currency is required");

        if (stock < 0) throw new DomainException("Stock must be >= 0");
        if (brand == null) throw new DomainException("Brand is required");
        if (category == null) throw new DomainException("Category is required"); // ✅ Category mandatory
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Store getStore() { return store; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Money getPrice() { return price; }
    public int getStock() { return stock; }
    public Brand getBrand() { return brand; }
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
