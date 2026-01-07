package com.lucasgodoy.lojaki.domain.order.model;

/**
 * Represents the status of an order.
 */
public enum Status {
    PENDING,    // Order created but not yet paid
    PAID,       // Payment received
    SHIPPED,    // Order shipped to customer
    DELIVERED,  // Order delivered successfully
    CANCELLED   // Order cancelled
}