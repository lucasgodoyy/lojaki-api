package com.lucasgodoy.lojaki.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;

    protected Product () {}

    public Product (UUID id, String name, String description, BigDecimal price){

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = true;

    }

    public void deactivate() {
        this.active = false;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }



}
