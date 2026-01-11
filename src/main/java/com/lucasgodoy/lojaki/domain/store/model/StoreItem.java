package com.lucasgodoy.lojaki.domain.store.model;

import com.lucasgodoy.lojaki.domain.product.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a specific product within a store.
 *
 * This class belongs to the Domain layer and contains only business rules.
 * It does NOT depend on persistence frameworks.
 *
 * Relationships:
 * - Many StoreItems belong to one Store
 * - Many StoreItems reference one Product
 */
public class StoreItem {

    private final UUID id;
    private final Store store;      // N:1
    private final Product product;  // N:1

    private BigDecimal price;
    private int stock;
    private boolean active;
    private Instant deletedAt;

    // ===== Private Constructor =====
    private StoreItem(UUID id, Store store, Product product, BigDecimal price, int stock) {
        validate(store, product, price, stock);
        this.id = id;
        this.store = store;
        this.product = product;
        this.price = price;
        this.stock = stock;
        this.active = true;
        this.deletedAt = null;
    }

    // ===== Factory Method =====
    public static StoreItem create(Store store, Product product, BigDecimal price, int stock) {
        return new StoreItem(UUID.randomUUID(), store, product, price, stock);
    }

    // ===== Business Methods =====
    public void updatePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = newPrice;
    }

    public void adjustStock(int delta) {
        int newStock = this.stock + delta;
        if (newStock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = newStock;
    }

    public void activate() {
        this.active = true;
        this.deletedAt = null;
    }

    public void deactivate() {
        this.active = false;
        this.deletedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(Store store, Product product, BigDecimal price, int stock) {
        if (store == null) throw new IllegalArgumentException("Store is required");
        if (product == null) throw new IllegalArgumentException("Product is required");
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price must be non-negative");
        if (stock < 0) throw new IllegalArgumentException("Stock must be non-negative");
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Store getStore() { return store; }
    public Product getProduct() { return product; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isActive() { return active; }
    public Instant getDeletedAt() { return deletedAt; }

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
