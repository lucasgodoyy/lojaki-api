package com.lucasgodoy.lojaki.domain.enums;

/**
 * Represents the status of an order.
 */
public enum OrderStatus {
    PENDING,    // Order created but not yet paid
    PAID,       // Payment received
    SHIPPED,    // Order shipped to customer
    DELIVERED,  // Order delivered successfully
    CANCELLED   // Order cancelled
}