package com.lucasgodoy.lojaki.domain.repository;

import com.lucasgodoy.lojaki.domain.order.model.Order;
import java.util.UUID;
import java.util.List;

// Repository interface for Order entity
public interface OrderRepository {

    // Finds an order by ID
    Order findById(UUID id);

    // Saves or updates an order
    void save(Order order);

    // Deletes an order
    void delete(Order order);

    // Returns all orders
    List<Order> findAll();
}
