package com.lucasgodoy.lojaki.domain.order.model;

import com.lucasgodoy.lojaki.domain.product.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents an item in an order.
 */
public class OrderItem {

    private UUID id;
    private Product product;
    private int quantity;
    private BigDecimal price;

    protected OrderItem() {}

    public OrderItem(UUID id, Product product, int quantity, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Calculates the total price of this item (price * quantity)
     */
    public BigDecimal total() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}