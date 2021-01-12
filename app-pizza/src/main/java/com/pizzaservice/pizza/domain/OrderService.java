package com.pizzaservice.pizza.domain;

import java.util.List;

public interface OrderService {
    void takeOrder(Order order);
    List<Order> getOrders();
}
