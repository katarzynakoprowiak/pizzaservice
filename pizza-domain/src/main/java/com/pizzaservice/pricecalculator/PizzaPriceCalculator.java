package com.pizzaservice.pricecalculator;

import com.pizzaservice.pizza.Pizza;

public interface PizzaPriceCalculator {
    double calculatePrice(Pizza pizza, double priceModifier);
}
