package com.lucasgodoy.lojaki.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * JPA entity representing the Customer-Store association.
 * Maps to the "customer_store" table in the database.
 */
@Entity
@Table(name = "customer_store")
public class CustomerStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    // ===== Constructors =====
    protected CustomerStoreEntity() {}

    public CustomerStoreEntity(UUID id, CustomerEntity customer, StoreEntity store,
                               Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customer = customer;
        this.store = store;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ===== Getters / Setters =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public StoreEntity getStore() { return store; }
    public void setStore(StoreEntity store) { this.store = store; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
