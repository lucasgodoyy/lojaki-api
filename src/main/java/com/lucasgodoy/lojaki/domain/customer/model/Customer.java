package com.lucasgodoy.lojaki.domain.customer.model;

import com.lucasgodoy.lojaki.domain.user.model.User;
import com.lucasgodoy.lojaki.domain.store.model.Store;
import com.lucasgodoy.lojaki.domain.exception.DomainException;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a customer owned by a store.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 *
 * - Customer belongs to ONE Store
 * - Customer references ONE User (1:1 relationship)
 * - Customer has profile info: firstName, lastName, phone, document
 */
public class Customer {

    /**
     * Unique identifier of the customer.
     */
    private final UUID id;

    /**
     * Store that owns this customer.
     */
    private final Store store;

    /**
     * Associated system user (1:1 relationship).
     * Must have role CUSTOMER.
     */
    private final User user;

    /**
     * Customer's first name. Required.
     */
    private String firstName;

    /**
     * Customer's last name. Required.
     */
    private String lastName;

    /**
     * Customer phone number. Required.
     */
    private String phone;

    /**
     * Customer document (CPF/CNPJ). Optional.
     */
    private String document;

    // ===== Private Constructor =====
    private Customer(UUID id,
                     Store store,
                     User user,
                     String firstName,
                     String lastName,
                     String phone,
                     String document) {

        validate(store, user, firstName, lastName, phone);

        this.id = id;
        this.store = store;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.document = document;
    }

    // ===== Factory Method =====
    /**
     * Creates a new Customer instance with generated UUID.
     *
     * @param store     Store that owns the customer
     * @param user      Associated User (must have CUSTOMER role)
     * @param firstName First name (required, min 2 chars)
     * @param lastName  Last name (required, min 2 chars)
     * @param phone     Phone number (required)
     * @return Customer instance
     */
    public static Customer create(Store store,
                                  User user,
                                  String firstName,
                                  String lastName,
                                  String phone) {

        return new Customer(
                UUID.randomUUID(),
                store,
                user,
                firstName,
                lastName,
                phone,
                null
        );
    }

    // ===== Business Methods =====
    /**
     * Updates the customer's profile.
     *
     * @param firstName New first name
     * @param lastName  New last name
     * @param phone     New phone number
     */
    public void updateProfile(String firstName, String lastName, String phone) {
        validate(this.store, this.user, firstName, lastName, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Sets or updates the customer's document.
     *
     * @param document CPF/CNPJ or other document
     */
    public void setDocument(String document) {
        this.document = document; // optional, set only when necessary
    }

    // ===== Validation =====
    private void validate(Store store, User user, String firstName, String lastName, String phone) {
        if (store == null) {
            throw new DomainException("Store is required");
        }
        if (user == null) {
            throw new DomainException("User is required");
        }

        if (firstName == null || firstName.trim().length() < 2) {
            throw new DomainException("First name is required and must have at least 2 characters");
        }
        if (lastName == null || lastName.trim().length() < 2) {
            throw new DomainException("Last name is required and must have at least 2 characters");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new DomainException("Phone number is required");
        }
    }

    // ===== Getters =====
    public UUID getId() { return id; }
    public Store getStore() { return store; }
    public User getUser() { return user; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getDocument() { return document; }

    // ===== Equals and HashCode =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
