package com.pizzaservice.pizza;

import java.util.List;

public interface Pizza {
    String getName();

    Ingredient getCrust();
    Ingredient getSauce();
    List<Ingredient> getToppings();
}
