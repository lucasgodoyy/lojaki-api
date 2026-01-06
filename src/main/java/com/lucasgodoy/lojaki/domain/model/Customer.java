package com.lucasgodoy.lojaki.domain.model;

import com.lucasgodoy.lojaki.domain.exception.DomainException;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a system customer.
 *
 * This class belongs to the Domain layer and contains only
 * business rules and invariants.
 *
 * It does NOT depend on frameworks, persistence, or infrastructure.
 */
public class Customer {

    /**
     * Unique identifier of the customer.
     */
    private final UUID id;

    /**
     * Associated User object (1:1 relationship).
     * Must have role CUSTOMER.
     */
    private final User user;

    /**
     * Customer's first name. Required, minimum 2 characters.
     */
    private String firstName;

    /**
     * Customer's last name. Required, minimum 2 characters.
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

    /**
     * Private constructor used by factory method.
     *
     * @param id        Unique UUID
     * @param user      Associated User
     * @param firstName First name
     * @param lastName  Last name
     * @param phone     Phone number
     * @param document  Document
     */
    private Customer(UUID id, User user, String firstName, String lastName, String phone, String document) {
        validate(user, firstName, lastName, phone);
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.document = document;
    }

    /**
     * Factory method to create a new Customer with generated UUID.
     *
     * @param user      Associated User
     * @param firstName First name
     * @param lastName  Last name
     * @param phone     Phone number
     * @param document  Document
     * @return Customer instance
     */
    public static Customer create(User user, String firstName, String lastName, String phone, String document) {
        return new Customer(UUID.randomUUID(), user, firstName, lastName, phone, document);
    }

    /**
     * Updates customer's profile.
     *
     * @param firstName New first name
     * @param lastName  New last name
     * @param phone     New phone number
     * @param document  New document
     */
    public void updateProfile(String firstName, String lastName, String phone, String document) {
        validate(this.user, firstName, lastName, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.document = document;
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
    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocument() {
        return document;
    }

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
