package com.pizzaservice.service;

import com.pizzaservice.model.PizzaType;
import com.pizzaservice.pizza.Pizza;

public interface PizzaFactory {
    Pizza makePizza(PizzaType pizzaType);
}
