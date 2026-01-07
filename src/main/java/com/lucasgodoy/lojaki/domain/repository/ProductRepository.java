package com.lucasgodoy.lojaki.domain.repository;

import com.lucasgodoy.lojaki.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Repository interface for Product entity
public interface ProductRepository extends JpaRepository<Product, UUID> {


}
