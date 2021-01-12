package com.pizzaservice.pizza.domain;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();
    void add(Order order);
    Order findById(Long orderId);
}
