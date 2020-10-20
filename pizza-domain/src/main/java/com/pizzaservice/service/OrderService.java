package com.pizzaservice.service;

import java.util.List;

public interface OrderService {
    void takeOrder(Order order);
    List<Order> getOrders();
}
