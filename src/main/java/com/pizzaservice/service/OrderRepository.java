package com.pizzaservice.service;

import java.util.List;

public interface OrderRepository {
    List<Order> getOrders();
    Order getOrder(int orderNumber);
    void addOrder(Order order);
}
