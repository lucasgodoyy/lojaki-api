package com.lucasgodoy.lojaki.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * JPA entity representing the association between Customer and Store.
 *
 * This is the join table for the many-to-many relationship.
 * Each CustomerStore links one Customer to one Store.
 */
@Entity
@Table(name = "customer_store")
public class CustomerStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Associated Customer.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    /**
     * Associated Store.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    /**
     * Timestamp of creation.
     */
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    /**
     * Timestamp of last update.
     */
    @Column(name = "updated_at")
    private Instant updatedAt;

    // ===== Constructors =====
    protected CustomerStoreEntity() {
        // JPA requires default constructor
    }

    public CustomerStoreEntity(UUID id, CustomerEntity customer, StoreEntity store, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customer = customer;
        this.store = store;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




    // ===== Getters and Setters =====
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
