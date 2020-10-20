package com.pizzaservice.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private static final Logger log = LogManager
            .getLogger(OrderServiceImpl.class.getName());

    private OrderStorage orderStorage;

    public OrderServiceImpl(OrderStorage orderStorage){
        this.orderStorage = orderStorage;
    }

    @Override
    public void takeOrder(Order order){
        orderStorage.addOrder(order);
        log.info("Added order #" + order.getOrderNumber());
    }

    public List<Order> getOrders(){
        return orderStorage.getOrders();
    }
}
