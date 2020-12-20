package com.pizzaservice.pizza;

import com.pizzaservice.model.PizzaType;

import java.util.List;

public interface Pizza{
    PizzaType getPizzaType();

    Ingredient getCrust();
    Ingredient getSauce();
    List<Ingredient> getToppings();
}
