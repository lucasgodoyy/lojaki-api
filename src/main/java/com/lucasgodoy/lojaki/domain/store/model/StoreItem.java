package com.lucasgodoy.lojaki.domain.store.model;


import com.lucasgodoy.lojaki.domain.product.model.Product;
import com.lucasgodoy.lojaki.domain.product.valueobject.Money;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product listing inside a store.
 *
 * This class belongs to the Domain layer and contains only business rules.
 * It does NOT depend on frameworks, persistence, or infrastructure.
 *
 * A StoreItem defines how a product is sold by a specific store,
 * including price, stock and availability.
 *
 * Relationships:
 * - 1 StoreItem : 1 Store
 * - 1 StoreItem : 1 Product
 */
public class StoreItem {

    /**
     * Unique identifier of the store item.
     */
    private final UUID id;

    /**
     * Store that owns this item.
     */
    private final Store store;

    /**
     * Product being sold.
     */
    private final Product product;

    /**
     * Sale price of the product in this store.
     */
    private Money price;

    /**
     * Available stock quantity.
     */
    private int stock;

    /**
     * Indicates whether the item is available for sale.
     */
    private boolean active;

    /**
     * Soft delete timestamp.
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
    private StoreItem(UUID id,
                      Store store,
                      Product product,
                      Money price,
                      int stock) {

        validate(store, product, price, stock);

        this.id = id;
        this.store = store;
        this.product = product;
        this.price = price;
        this.stock = stock;
        this.active = true;
        this.deletedAt = null;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====

    /**
     * Creates a new StoreItem.
     *
     * @param store Store selling the product
     * @param product Product being sold
     * @param price Sale price
     * @param stock Initial stock quantity
     * @return new StoreItem instance
     */
    public static StoreItem create(Store store,
                                   Product product,
                                   Money price,
                                   int stock) {
        return new StoreItem(
                UUID.randomUUID(),
                store,
                product,
                price,
                stock
        );
    }

    // ===== Business Methods =====

    /**
     * Updates price and stock.
     */
    public void update(Money price, int stock) {
        validate(store, product, price, stock);
        this.price = price;
        this.stock = stock;
        this.updatedAt = Instant.now();
    }

    /**
     * Decreases stock after a sale.
     */
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (this.stock < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
        this.stock -= quantity;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the item as unavailable.
     */
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }

    /**
     * Marks the item as available.
     */
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    /**
     * Soft deletes the store item.
     */
    public void softDelete() {
        this.deletedAt = Instant.now();
        this.active = false;
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(Store store,
                          Product product,
                          Money price,
                          int stock) {

        if (store == null) {
            throw new IllegalArgumentException("Store is required");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product is required");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price is required");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock must be >= 0");
        }
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Store getStore() { return store; }
    public Product getProduct() { return product; }
    public Money getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isActive() { return active; }
    public Instant getDeletedAt() { return deletedAt; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // ===== Equals and HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreItem)) return false;
        StoreItem that = (StoreItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
