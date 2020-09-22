package com.pizzaservice.service;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository{
    //TODO: should it be in caps?
    private static volatile OrderRepositoryImpl INSTANCE = null;
    private final List<Order> orders;

    public synchronized static OrderRepositoryImpl getInstance() {
        if (INSTANCE == null){
            INSTANCE = new OrderRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Order getOrder(int orderNumber) {
        //TODO: stream perhaps?
        return null;
    }

    @Override
    public void addOrder(Order order) {
        order.setOrderNumber(orders.size()+1);
        orders.add(order);
    }

    private OrderRepositoryImpl(){
        orders = new ArrayList<>();
    }
}
