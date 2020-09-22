package com.pizzaservice.service;

import java.util.List;

public interface OrderService {
    void takeOrder(Order order);
    List<Order> getOrders();
    Order getOrderByNumber(int orderNumber);
    String printOrders(List<Order> orders);
}
