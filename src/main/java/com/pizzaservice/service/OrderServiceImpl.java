package com.pizzaservice.service;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public void takeOrder(Order order){
        repository.addOrder(order);
    }

    public List<Order> getOrders(){
        return repository.getOrders();
    }

    public Order getOrderByNumber(int orderNumber){
        return repository.getOrder(orderNumber);
    }

    @Override
    public String printOrders(List<Order> orders) {
        if (orders.isEmpty()){
            return "There are no orders";
        }

        StringBuilder builder = new StringBuilder();
        for (Order order: orders){
            builder.append(order);
        }
        return builder.toString();
    }

}
