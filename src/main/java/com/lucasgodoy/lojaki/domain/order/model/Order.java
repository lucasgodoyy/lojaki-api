package com.lucasgodoy.lojaki.domain.order.model;

import com.lucasgodoy.lojaki.domain.store.model.Store;
import com.lucasgodoy.lojaki.domain.user.model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a customer order in the system.
 *
 * Shopify-like model:
 * - Order belongs to ONE Store
 * - Order references ONE User (customer)
 * - Order has N OrderItems
 */
public class Order {

    /**
     * Unique identifier of the order.
     */
    private final UUID id;

    /**
     * Store where the order is placed.
     */
    private final Store store;

    /**
     * Customer who placed the order.
     */
    private final User user;

    /**
     * List of items in the order.
     */
    private final List<OrderItem> items;

    /**
     * Current status of the order.
     */
    private Status status;

    /**
     * Indicates whether the order is active.
     */
    private boolean active;

    /**
     * Timestamp of order creation.
     */
    private final Instant createdAt;

    /**
     * Timestamp of last update.
     */
    private Instant updatedAt;

    // ===== Private Constructor =====
    private Order(UUID id, Store store, User user, List<OrderItem> items) {
        validate(store, user, items);
        this.id = id;
        this.store = store;
        this.user = user;
        this.items = new ArrayList<>(items);
        this.status = Status.PENDING;
        this.active = true;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory Method =====
    public static Order create(Store store, User user, List<OrderItem> items) {
        return new Order(UUID.randomUUID(), store, user, items);
    }

    // ===== Business Methods =====
    public void cancel() {
        this.active = false;
        this.status = Status.CANCELLED;
        this.updatedAt = Instant.now();
    }

    public void complete() {
        this.status = Status.DELIVERED;
        this.updatedAt = Instant.now();
    }

    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = Instant.now();
    }

    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("OrderItem cannot be null");
        this.items.add(item);
        this.updatedAt = Instant.now();
    }

    // ===== Validation =====
    private void validate(Store store, User user, List<OrderItem> items) {
        if (store == null) throw new IllegalArgumentException("Store is required");
        if (user == null) throw new IllegalArgumentException("User is required");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("Order must have at least one item");
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Store getStore() { return store; }
    public User getUser() { return user; }
    public List<OrderItem> getItems() { return items; }
    public Status getStatus() { return status; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // ===== Equals and HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
