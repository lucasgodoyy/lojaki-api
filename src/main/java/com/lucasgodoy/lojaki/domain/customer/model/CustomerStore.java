package com.lucasgodoy.lojaki.domain.customer.model;

import com.lucasgodoy.lojaki.domain.store.model.Store;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents the relationship between a Customer and a Store.
 *
 * This class belongs to the Domain layer and contains only business rules.
 */
public class CustomerStore {

    private final UUID id;

    private final Customer customer;

    private final Store store;

    private final Instant createdAt;

    private Instant updatedAt;

    // ===== Constructor =====
    private CustomerStore(UUID id, Customer customer, Store store) {
        if (customer == null) throw new IllegalArgumentException("Customer is required");
        if (store == null) throw new IllegalArgumentException("Store is required");

        this.id = id;
        this.customer = customer;
        this.store = store;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ===== Factory =====
    public static CustomerStore create(Customer customer, Store store) {
        return new CustomerStore(UUID.randomUUID(), customer, store);
    }

    // ===== Business Methods =====
    public void touch() {
        this.updatedAt = Instant.now();
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Store getStore() { return store; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // ===== Equals / HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerStore)) return false;
        CustomerStore that = (CustomerStore) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
