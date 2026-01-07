package com.lucasgodoy.lojaki.domain.order.model;

import com.lucasgodoy.lojaki.domain.user.model.User;
import com.lucasgodoy.lojaki.domain.product.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Represents a customer order in the domain layer.
 *
 * This class belongs to the Domain layer and contains only business rules.
 * It does NOT depend on persistence frameworks or infrastructure.
 */
public class Order {

    /**
     * Unique identifier of the order.
     */
    private final UUID id;

    /**
     * User who placed the order.
     */
    private final User user;

    /**
     * List of products included in the order.
     */
    private final List<Product> products;

    /**
     * Current status of the order.
     */
    private Status status;

    /**
     * Indicates whether the order is active.
     */
    private boolean active;

    /**
     * Protected constructor required for frameworks or serialization.
     */
    protected Order() {
        this.id = null;
        this.user = null;
        this.products = null;
    }

    /**
     * Creates a new order with a user and products.
     *
     * Business rules:
     * - User is mandatory
     * - At least one product is required
     * - Orders start with PENDING status and active=true
     *
     * @param id       unique identifier
     * @param user     user placing the order
     * @param products list of products
     */
    public Order(UUID id, User user, List<Product> products) {
        if (user == null) throw new IllegalArgumentException("User is required");
        if (products == null || products.isEmpty()) throw new IllegalArgumentException("At least one product is required");

        this.id = id;
        this.user = user;
        this.products = products;
        this.status = Status.PENDING;
        this.active = true;
    }

    // ===== Business Methods =====

    /**
     * Cancels the order.
     * Sets status to CANCELLED and active to false.
     */
    public void cancel() {
        this.active = false;
        this.status = Status.CANCELLED;
    }

    /**
     * Completes the order.
     * Sets status to DELIVERED.
     */
    public void complete() {
        this.status = Status.DELIVERED;
    }

    /**
     * Updates the order status.
     *
     * @param status new status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    // ===== Getters =====

    public UUID getId() { return id; }
    public User getUser() { return user; }
    public List<Product> getProducts() { return products; }
    public Status getStatus() { return status; }
    public boolean isActive() { return active; }
}
