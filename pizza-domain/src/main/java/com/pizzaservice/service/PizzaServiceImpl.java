package com.pizzaservice.service;

import com.pizzaservice.pizza.Pizza;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PizzaServiceImpl implements PizzaService{
    private static final Logger log = LogManager
            .getLogger(PizzaServiceImpl.class.getName());

    private final PizzaFactoryImpl factory;

    public PizzaServiceImpl(PizzaFactoryImpl factory){
        this.factory = factory;
    }

    @Override
    public List<Pizza> makePizza(Order order) {
        log.info("Prepared order #" + order.getOrderNumber());
        return order.getOrderedItems().stream()
                .map(factory::makePizza)
                .collect(toList());
    }
}
