package com.lucasgodoy.lojaki.domain.customer.model;

import com.lucasgodoy.lojaki.domain.user.model.User;
import com.lucasgodoy.lojaki.domain.exception.DomainException;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a customer in the system.
 *
 * A Customer is a system user with role CUSTOMER and contains
 * additional profile information such as name and contact details.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * It does NOT depend on frameworks, persistence, or infrastructure.
 *
 * Relationships:
 * - 1 Customer : 1 User (must have role CUSTOMER)
 */
public class Customer {

    /**
     * Unique identifier of the customer.
     */
    private final UUID id;

    /**
     * Associated system user (1:1 relationship).
     * Must have role CUSTOMER.
     */
    private final User user;

    /**
     * Customer's first name. Required, at least 2 characters.
     */
    private String firstName;

    /**
     * Customer's last name. Required, at least 2 characters.
     */
    private String lastName;

    /**
     * Customer phone number. Required.
     */
    private String phone;

    /**
     * Customer document (CPF/CNPJ). Optional.
     * Only set when needed, e.g., during payment or billing.
     */
    private String document;

    // ===== Private Constructor =====
    private Customer(UUID id, User user, String firstName, String lastName, String phone, String document) {
        validate(user, firstName, lastName, phone);
        this.id = id;
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
     * @param user      Associated User (must have CUSTOMER role)
     * @param firstName First name (required, min 2 chars)
     * @param lastName  Last name (required, min 2 chars)
     * @param phone     Phone number (required)
     * @return Customer instance
     */
    public static Customer create(User user, String firstName, String lastName, String phone) {
        return new Customer(UUID.randomUUID(), user, firstName, lastName, phone, null);
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
        validate(this.user, firstName, lastName, phone);
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
    private void validate(User user, String firstName, String lastName, String phone) {
        if (user == null) {
            throw new DomainException("User is required");
        }
        if (!"CUSTOMER".equals(user.getRole().name())) {
            throw new DomainException("User must have CUSTOMER role");
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
