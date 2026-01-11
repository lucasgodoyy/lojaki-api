package com.lucasgodoy.lojaki.domain.order.model;

import com.lucasgodoy.lojaki.domain.product.model.Product;
import com.lucasgodoy.lojaki.domain.exception.DomainException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an item in an order.
 *
 * Each OrderItem references a single Product from the store.
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * Relationships:
 * - N OrderItems belong to 1 Order
 * - 1 OrderItem references 1 Product
 */
public class OrderItem {

    /**
     * Unique identifier of the order item.
     */
    private final UUID id;

    /**
     * Product referenced by this order item. Required.
     */
    private final Product product;

    /**
     * Quantity of the product in this order item. Must be >= 1.
     */
    private int quantity;

    /**
     * Price of the product at the time of order. Required, >= 0.
     */
    private BigDecimal price;

    // ===== Private Constructor =====
    private OrderItem(UUID id, Product product, int quantity, BigDecimal price) {
        validate(product, quantity, price);
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // ===== Factory Method =====
    /**
     * Creates a new OrderItem instance with generated UUID.
     *
     * @param product  Product being ordered
     * @param quantity Quantity of product
     * @param price    Price at order time
     * @return OrderItem instance
     */
    public static OrderItem create(Product product, int quantity, BigDecimal price) {
        return new OrderItem(UUID.randomUUID(), product, quantity, price);
    }

    // ===== Business Methods =====
    /**
     * Calculates the total price of this item (price * quantity)
     *
     * @return total price
     */
    public BigDecimal total() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Updates quantity and recalculates as needed.
     *
     * @param quantity new quantity
     */
    public void updateQuantity(int quantity) {
        if (quantity < 1) {
            throw new DomainException("Quantity must be at least 1");
        }
        this.quantity = quantity;
    }

    // ===== Validation =====
    private void validate(Product product, int quantity, BigDecimal price) {
        if (product == null) {
            throw new DomainException("Product is required");
        }
        if (quantity < 1) {
            throw new DomainException("Quantity must be at least 1");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Price must be >= 0");
        }
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }

    // ===== Equals and HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
