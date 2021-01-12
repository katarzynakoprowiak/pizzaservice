package com.pizzaservice.pizza.domain;

import java.util.List;

public interface PizzaService {
    List<Pizza> makePizza(Order order);
}
