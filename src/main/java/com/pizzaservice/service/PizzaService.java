package com.pizzaservice.service;

import java.util.ArrayList;
import java.util.List;

public class PizzaService {
    private final List<Order> orders;
    private final PizzaFactory factory;

    public PizzaService(PizzaFactory factory){
        this.factory = factory;
        orders = new ArrayList<>();
    }

    public void takeOrder(Order order){
        order.setOrderNumber(orders.size()+1);
        orders.add(order);
    }
    
    public String printOrders(){
        if (orders.isEmpty()){
            return "There are no orders";
        }

        StringBuilder builder = new StringBuilder();
        for (Order order: orders){
            builder.append(order);
        }
        return builder.toString();
    }

    public String printMenu(){
        return factory.printMenu();
    }
}
