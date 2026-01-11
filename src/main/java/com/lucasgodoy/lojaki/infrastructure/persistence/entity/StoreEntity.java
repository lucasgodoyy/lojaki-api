package com.lucasgodoy.lojaki.infrastructure.persistence.entity;


import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * JPA entity representing a Store (seller) for persistence.
 *
 * A Store represents a seller that can sell products
 * from multiple brands.
 */
@Entity
@Table(name = "stores")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    // ===== Relationships =====

    /**
     * One-to-many relationship with StoreItems.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreItemEntity> storeItems = new ArrayList<>();

    /**
     * One-to-many relationship with Brands.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BrandEntity> brands = new ArrayList<>();

    /**
     * One-to-many relationship with Categories.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> categories = new ArrayList<>();

    /**
     * One-to-many relationship with Products.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products = new ArrayList<>();

    /**
     * One-to-many relationship with Orders.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();

    /**
     * Many-to-many with Customer via CustomerStore.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerStoreEntity> customerStores = new ArrayList<>();

    // ===== Constructors =====
    protected StoreEntity() {
        // JPA default constructor
    }

    public StoreEntity(UUID id, String name, String description, boolean active,
                       Instant deletedAt, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Methods
    public void addStoreItem(StoreItemEntity item) {
        storeItems.add(item);
        item.setStore(this);
    }

    public void removeStoreItem(StoreItemEntity item) {
        storeItems.remove(item);
        item.setStore(null);
    }



    // ===== Getters and Setters =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Instant deletedAt) { this.deletedAt = deletedAt; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    public List<StoreItemEntity> getStoreItems() { return storeItems; }
    public List<BrandEntity> getBrands() { return brands; }
    public List<CategoryEntity> getCategories() { return categories; }
    public List<ProductEntity> getProducts() { return products; }
    public List<OrderEntity> getOrders() { return orders; }
    public List<CustomerStoreEntity> getCustomerStores() { return customerStores; }
}
