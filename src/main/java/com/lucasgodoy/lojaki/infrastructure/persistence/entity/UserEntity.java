package com.lucasgodoy.lojaki.infrastructure.persistence.entity;

import com.lucasgodoy.lojaki.domain.user.model.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * JPA Entity representing a system user for persistence.
 *
 * Represents the account credentials and role of a system user.
 * - Each user may have an associated Customer profile if the user is a CUSTOMER.
 * - Each user may place multiple Orders.
 *
 * Note:
 * Guest orders (non-registered users) can be supported later if needed.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    // ===== Attributes =====

    /** Unique identifier of the user, automatically generated as UUID. */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /** Email of the user. Must be unique and not null. */
    @Column(unique = true, nullable = false)
    private String email;

    /** Role of the user (ADMIN, CUSTOMER, STAFF). Stored as a string in DB. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /** Indicates if the user account is active. */
    @Column(nullable = false)
    private boolean active;

    // ===== Relationships =====

    /**
     * One-to-one association with CustomerEntity.
     * Only registered users have a Customer profile.
     * Cascade ensures changes propagate from User to Customer.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerEntity customer;

    /**
     * One-to-many association with orders placed by this user.
     */
    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders = new ArrayList<>();

    // ===== Constructors =====

    /** Default constructor required by JPA. */
    protected UserEntity() {}

    /**
     * Constructs a UserEntity with all required fields.
     *
     * @param id     Unique identifier
     * @param email  User email, must be unique
     * @param role   Role of the user
     * @param active Active status
     */
    public UserEntity(UUID id, String email, Role role, boolean active) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    // ===== Getters and Setters =====

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public List<OrderEntity> getOrders() { return orders; }
    public void setOrders(List<OrderEntity> orders) { this.orders = orders; }

    // ===== Equals and HashCode =====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
