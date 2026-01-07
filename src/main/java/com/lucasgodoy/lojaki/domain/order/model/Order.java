package com.lucasgodoy.lojaki.domain.order.model;

import com.lucasgodoy.lojaki.domain.user.model.User;
import jakarta.persistence.*;

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
    private Status status;

    private boolean active;

    protected Order() {}

    public Order(UUID id, User user) {
        this.id = id;
        this.user = user;

        this.status = Status.PENDING;
        this.active = true;
    }


    /**
     * Changes the status of the order.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Cancels the order.
     */
    public void deactivate() {
        this.active = false;
        this.status = Status.CANCELLED;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }


    public Status getStatus() {
        return status;
    }

    public boolean isActive() {
        return active;
    }
}