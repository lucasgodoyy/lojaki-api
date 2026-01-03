package com.lucasgodoy.lojaki.domain.model;

import com.lucasgodoy.lojaki.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a customer order.
 */
public class Order {

    private UUID id;
    private User user;
    private List<OrderItem> items;
    private OrderStatus status;
    private boolean active;

    protected Order() {
        this.items = new ArrayList<>();
    }

    public Order(UUID id, User user) {
        this.id = id;
        this.user = user;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.active = true;
    }

    /**
     * Adds an item to the order.
     */
    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    /**
     * Calculates the total value of the order.
     */
    public BigDecimal total() {
        return items.stream()
                .map(OrderItem::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Changes the status of the order.
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Cancels the order.
     */
    public void deactivate() {
        this.active = false;
        this.status = OrderStatus.CANCELLED;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return active;
    }
}