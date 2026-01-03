package com.lucasgodoy.lojaki.api.controller;

import com.lucasgodoy.lojaki.domain.model.Order;
import com.lucasgodoy.lojaki.application.service.OrderService;
import java.util.List;
import java.util.UUID;

// Controller for Order entity
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Creates a new order
    public void createOrder(Order order) {
        orderService.createOrder(order);
    }

    // Updates an existing order
    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    // Deletes an order
    public void deleteOrder(Order order) {
        orderService.deleteOrder(order);
    }

    // Finds an order by ID
    public Order getOrderById(UUID id) {
        return orderService.getOrderById(id);
    }

    // Lists all orders
    public List<Order> listAllOrders() {
        return orderService.listAllOrders();
    }
}
