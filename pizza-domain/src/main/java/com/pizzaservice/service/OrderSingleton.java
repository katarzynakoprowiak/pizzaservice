package com.pizzaservice.service;

import java.util.ArrayList;
import java.util.List;

public class OrderSingleton implements OrderStorage {
    private static OrderSingleton INSTANCE = null;
    private final List<Order> orders;

    public static OrderSingleton getInstance() {
        if (INSTANCE == null){
            INSTANCE = new OrderSingleton();
        }
        return INSTANCE;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        order.setOrderNumber(orders.size()+1);
        orders.add(order);
    }

    private OrderSingleton(){
        orders = new ArrayList<>();
    }
}
