package com.lucasgodoy.lojaki.domain.model;

import com.lucasgodoy.lojaki.domain.exception.DomainException;

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
     * Product price amount. Required, must be >= 0.
     */
    private BigDecimal priceAmount;

    /**
     * Product price currency. Required. Default is "AUD".
     */
    private String priceCurrency;

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
                    BigDecimal priceAmount,
                    String priceCurrency,
                    Category category) {
        validate(name, priceAmount, priceCurrency);
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
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
     * @param name Name of the product
     * @param description Optional description
     * @param priceAmount Price amount (>= 0)
     * @param priceCurrency Currency code (BRL, USD, etc.)
     * @param category Optional category
     * @return New Product instance
     */
    public static Product create(String name,
                                 String description,
                                 BigDecimal priceAmount,
                                 String priceCurrency,
                                 Category category) {
        return new Product(UUID.randomUUID(), name, description, priceAmount, priceCurrency, category);
    }

    // ===== Business Methods =====
    /**
     * Updates product details. Price and category can be updated.
     *
     * @param name New name
     * @param description New description
     * @param priceAmount New price amount
     * @param priceCurrency New price currency
     * @param category New category
     */
    public void update(String name,
                       String description,
                       BigDecimal priceAmount,
                       String priceCurrency,
                       Category category) {
        validate(name, priceAmount, priceCurrency);
        this.name = name;
        this.description = description;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
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
    private void validate(String name, BigDecimal priceAmount, String priceCurrency) {
        if (name == null || name.trim().length() < 3 || name.trim().length() > 100) {
            throw new DomainException("Product name is required and must be between 3 and 100 characters");
        }
        if (priceAmount == null || priceAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Price amount is required and must be >= 0");
        }
        if (priceCurrency == null || priceCurrency.trim().isEmpty()) {
            throw new DomainException("Price currency is required");
        }

    }

    // ===== Getters =====
    public UUID getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public BigDecimal getPriceAmount() { return priceAmount; }

    public String getPriceCurrency() { return priceCurrency; }

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
