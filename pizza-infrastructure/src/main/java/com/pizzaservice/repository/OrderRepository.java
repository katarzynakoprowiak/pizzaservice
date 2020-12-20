package com.pizzaservice.repository;

import com.pizzaservice.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();
    void add(Order order);
    Order get(Long orderId);
}
