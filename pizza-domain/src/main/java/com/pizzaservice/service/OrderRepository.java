package com.pizzaservice.service;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {
    List<Order> getOrders() throws SQLException;
    //void addOrder(Order order);
    int addOrder(Order order) throws SQLException;
}
