package com.lucasgodoy.lojaki.application.service;

import com.lucasgodoy.lojaki.domain.model.Order;
import com.lucasgodoy.lojaki.domain.repository.OrderRepository;
import java.util.List;
import java.util.UUID;

// Service layer for Order entity
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Creates a new order
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    // Updates an existing order
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    // Deletes an order
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    // Finds an order by ID
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    // Lists all orders
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }
}
