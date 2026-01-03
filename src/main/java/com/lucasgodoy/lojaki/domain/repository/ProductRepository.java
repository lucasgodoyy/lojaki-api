package com.lucasgodoy.lojaki.domain.repository;

import com.lucasgodoy.lojaki.domain.model.Product;
import java.util.UUID;
import java.util.List;

// Repository interface for Product entity
public interface ProductRepository {

    // Finds a product by ID
    Product findById(UUID id);

    // Saves or updates a product
    void save(Product product);

    // Deletes a product
    void delete(Product product);

    // Returns all products
    List<Product> findAll();
}
