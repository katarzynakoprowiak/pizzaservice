package com.pizzaservice.pizza;

import java.util.List;

public interface Pizza {
    PizzaType getPizzaType();

    Ingredient getCrust();
    Ingredient getSauce();
    List<Ingredient> getToppings();
}
