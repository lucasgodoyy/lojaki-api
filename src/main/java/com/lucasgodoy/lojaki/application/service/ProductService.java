package com.lucasgodoy.lojaki.application.service;

import com.lucasgodoy.lojaki.domain.product.model.Product;
import com.lucasgodoy.lojaki.domain.repository.ProductRepository;
import java.util.List;

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


    // Lists all products
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }
}
