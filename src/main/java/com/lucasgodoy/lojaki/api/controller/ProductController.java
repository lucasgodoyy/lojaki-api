package com.lucasgodoy.lojaki.api.controller;

import com.lucasgodoy.lojaki.domain.model.Product;
import com.lucasgodoy.lojaki.application.service.ProductService;
import java.util.List;
import java.util.UUID;

// Controller for Product entity
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Creates a new product
    public void createProduct(Product product) {
        productService.createProduct(product);
    }

    // Updates an existing product
    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    // Deletes a product
    public void deleteProduct(Product product) {
        productService.deleteProduct(product);
    }

    // Finds a product by ID
    public Product getProductById(UUID id) {
        return productService.getProductById(id);
    }

    // Lists all products
    public List<Product> listAllProducts() {
        return productService.listAllProducts();
    }
}
