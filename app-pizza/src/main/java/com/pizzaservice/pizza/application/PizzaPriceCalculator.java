package com.pizzaservice.pizza.application;

import com.pizzaservice.pizza.domain.Pizza;

import java.math.BigDecimal;

public interface PizzaPriceCalculator {
    BigDecimal calculatePrice(Pizza pizza, double priceModifier);
}
