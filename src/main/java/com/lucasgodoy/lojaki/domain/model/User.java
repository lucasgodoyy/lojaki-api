package com.lucasgodoy.lojaki.domain.model;

import com.lucasgodoy.lojaki.domain.enums.UserRole;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private boolean active;

    protected User() {}

    public User(UUID id, String name, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = true;
    }

    /**
     * Deactivate the user.
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

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}
