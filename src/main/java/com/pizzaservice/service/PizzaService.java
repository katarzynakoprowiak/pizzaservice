package com.pizzaservice.service;

import com.pizzaservice.pizza.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> makePizza(Order order);
}
