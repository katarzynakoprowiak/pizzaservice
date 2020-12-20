package com.pizzaservice.service;

import com.pizzaservice.model.Order;

import java.util.List;

public interface OrderService {
    //Optional<Set<ConstraintViolation<Order>>> takeOrder(Order order);
    void takeOrder(Order order);
    List<Order> getOrders();
}
