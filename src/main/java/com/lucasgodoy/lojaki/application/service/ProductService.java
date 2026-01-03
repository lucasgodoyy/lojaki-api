package com.lucasgodoy.lojaki.application.service;

import com.lucasgodoy.lojaki.domain.model.Product;
import com.lucasgodoy.lojaki.domain.repository.ProductRepository;
import java.util.List;
import java.util.UUID;

// Service layer for Product entity
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Creates a new product
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    // Updates an existing product
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    // Deletes a product
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    // Finds a product by ID
    public Product getProductById(UUID id) {
        return productRepository.findById(id);
    }

    // Lists all products
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }
}
