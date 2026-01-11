package com.lucasgodoy.lojaki.domain.customer.model;

import com.lucasgodoy.lojaki.domain.store.model.Store;
import com.lucasgodoy.lojaki.domain.exception.DomainException;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents an association between a Customer and a Store.
 *
 * This class belongs to the Domain layer and contains only business rules.
 * It models the fact that a customer can be linked to one or more stores.
 *
 * Relationships:
 * - N:1 with Customer
 * - N:1 with Store
 */
public class CustomerStore {

    /**
     * Unique identifier of the association.
     */
    private final UUID id;

    /**
     * Customer associated with this store.
     */
    private final Customer customer;

    /**
     * Store associated with this customer.
     */
    private final Store store;

    // ===== Private Constructor =====
    private CustomerStore(UUID id, Customer customer, Store store) {
        validate(customer, store);
        this.id = id;
        this.customer = customer;
        this.store = store;
    }

    // ===== Factory Method =====
    /**
     * Creates a new CustomerStore association.
     *
     * @param customer Customer instance (must not be null)
     * @param store    Store instance (must not be null)
     * @return CustomerStore instance
     */
    public static CustomerStore create(Customer customer, Store store) {
        return new CustomerStore(UUID.randomUUID(), customer, store);
    }

    // ===== Validation =====
    private void validate(Customer customer, Store store) {
        if (customer == null) {
            throw new DomainException("Customer is required");
        }
        if (store == null) {
            throw new DomainException("Store is required");
        }
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Store getStore() { return store; }

    // ===== Equals and HashCode =====
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
