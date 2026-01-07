package com.lucasgodoy.lojaki.api.controller;

import com.lucasgodoy.lojaki.domain.product.model.Product;
import com.lucasgodoy.lojaki.application.service.ProductService;
import java.util.List;

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


    // Lists all products
    public List<Product> listAllProducts() {
        return productService.listAllProducts();
    }
}
