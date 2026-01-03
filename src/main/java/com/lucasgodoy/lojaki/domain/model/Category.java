package com.lucasgodoy.lojaki.domain.model;

import java.util.UUID;

public class Category {

    private UUID id;
    private String name;
    private boolean active;
    protected Category() {}

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.active = true;
    }

    /**
     * Deactivate the category.
     */
    public void deactivate() {
        this.active = false;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }



}
