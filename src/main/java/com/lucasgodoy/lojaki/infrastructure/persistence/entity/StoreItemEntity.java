package com.lucasgodoy.lojaki.infrastructure.persistence.entity;

import com.lucasgodoy.lojaki.domain.product.valueobject.Money;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

/**
 * JPA entity representing a StoreItem (product listing in a store) for persistence.
 * Maps to the "store_items" table in the database.
 *
 * A StoreItem defines how a product is sold by a specific store,
 * including price, stock and availability.
 */
@Entity
@Table(name = "store_items")
public class StoreItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Store that sells this item.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    /**
     * Product being sold.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    /**
     * Sale price of the product in this store.
     */
    @Embedded
    private Money price;

    /**
     * Available stock quantity.
     */
    @Column(nullable = false)
    private int stock;

    /**
     * Indicates whether the item is active/available for sale.
     */
    @Column(nullable = false)
    private boolean active;

    /**
     * Soft delete timestamp.
     */
    private Instant deletedAt;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    // ===== Constructors =====
    protected StoreItemEntity() {
        // JPA requires default constructor
    }

    public StoreItemEntity(UUID id,
                           StoreEntity store,
                           ProductEntity product,
                           Money price,
                           int stock,
                           boolean active,
                           Instant createdAt,
                           Instant updatedAt,
                           Instant deletedAt) {
        this.id = id;
        this.store = store;
        this.product = product;
        this.price = price;
        this.stock = stock;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // ===== Getters and Setters =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public StoreEntity getStore() { return store; }
    public void setStore(StoreEntity store) { this.store = store; }

    public ProductEntity getProduct() { return product; }
    public void setProduct(ProductEntity product) { this.product = product; }

    public Money getPrice() { return price; }
    public void setPrice(Money price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Instant deletedAt) { this.deletedAt = deletedAt; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
