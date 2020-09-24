package com.pizzaservice.service;

import com.pizzaservice.pizza.Pizza;
import com.pizzaservice.pizza.PizzaType;

import java.util.ArrayList;
import java.util.List;

public class PizzaServiceImpl implements PizzaService{

    private final PizzaFactoryImpl factory;

    public PizzaServiceImpl(PizzaFactoryImpl factory){
        this.factory = factory;
    }

    @Override
    public List<Pizza> makePizza(Order order) {
        //TODO: change to lambda or stream
        List<Pizza> pizzas = new ArrayList<>();

        for (PizzaType type: order.getOrderedItems()){
            pizzas.add(factory.makePizza(type));
        }

        return pizzas;
    }

    //TODO: should it be here?
    // Perhaps it should be a list of PizzaTypes to be dynamically loaded as menu
    public String printMenu(){
        return factory.printMenu();
    }
}
