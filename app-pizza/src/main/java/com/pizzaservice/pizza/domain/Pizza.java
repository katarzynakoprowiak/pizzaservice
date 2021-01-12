package com.pizzaservice.pizza.domain;

import java.util.List;

public interface Pizza{
    PizzaType getPizzaType();

    Ingredient getCrust();
    Ingredient getSauce();
    List<Ingredient> getToppings();
}
