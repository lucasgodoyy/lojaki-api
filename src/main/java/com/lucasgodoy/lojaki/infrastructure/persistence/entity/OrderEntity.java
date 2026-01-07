package com.lucasgodoy.lojaki.infrastructure.persistence.entity;

import com.lucasgodoy.lojaki.domain.order.model.Status;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * JPA Entity representing an order for persistence.
 *
 * This class maps the Order domain model to the database.
 * It contains no business logic, only persistence-related annotations.
 */
@Entity
@Table(name = "orders")
public class OrderEntity {

    /**
     * Unique identifier of the order.
     * Automatically generated as UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * User who placed the order.
     * Many orders can belong to one user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * List of products in the order.
     * Many-to-many relationship with join table order_products.
     */
    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    /**
     * Current status of the order.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /**
     * Indicates whether the order is active.
     */
    @Column(nullable = false)
    private boolean active;

    /**
     * Default constructor required by JPA.
     */
    protected OrderEntity() {}

    /**
     * Constructor to create an OrderEntity instance.
     *
     * @param id       unique identifier
     * @param user     associated user entity
     * @param products list of product entities
     * @param status   order status
     * @param active   whether the order is active
     */
    public OrderEntity(UUID id, UserEntity user, List<ProductEntity> products, Status status, boolean active) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.status = status;
        this.active = active;
    }

    // ===== Getters and Setters =====

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public List<ProductEntity> getProducts() { return products; }
    public void setProducts(List<ProductEntity> products) { this.products = products; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
