package com.pizzaservice.service;

import com.pizzaservice.pizza.Pizza;
import com.pizzaservice.pizza.PizzaType;

public interface PizzaFactory {
    Pizza makePizza(PizzaType pizzaType);
}
