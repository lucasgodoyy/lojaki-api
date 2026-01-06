package com.lucasgodoy.lojaki.domain.model;

import com.lucasgodoy.lojaki.domain.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a customer order.
 */

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private boolean active;

    protected Order() {}

    public Order(UUID id, User user) {
        this.id = id;
        this.user = user;

        this.status = OrderStatus.PENDING;
        this.active = true;
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


    public OrderStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return active;
    }
}