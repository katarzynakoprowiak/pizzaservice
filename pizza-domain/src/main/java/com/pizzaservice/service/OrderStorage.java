package com.pizzaservice.service;

import java.util.List;

public interface OrderStorage {
    List<Order> getOrders();
    void addOrder(Order order);
}
