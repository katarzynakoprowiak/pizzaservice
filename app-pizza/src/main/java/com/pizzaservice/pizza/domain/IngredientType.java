package com.pizzaservice.pizza.domain;

import com.pizzaservice.pizza.application.PriceCalculable;

import java.math.BigDecimal;

public enum IngredientType implements PriceCalculable {
    CRUST(240, BigDecimal.valueOf(4.3)),
    ROLLED_CRUST(200, BigDecimal.valueOf(4.2)),
    TOMATO_SAUCE(50, BigDecimal.valueOf(1.25)),
    CHEESE(100, BigDecimal.valueOf(1.47)),
    HAM(40, BigDecimal.valueOf(2.31)),
    MUSHROOMS(25, BigDecimal.valueOf(1.5)),
    ARTICHOKES(20, BigDecimal.valueOf(5.7)),
    BELL_PEPPER(20, BigDecimal.valueOf(2.14)),
    ONION(20, BigDecimal.valueOf(0.14));

    private final int weight;
    private final BigDecimal price;

    IngredientType(int weight, BigDecimal price){
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String toString(){
        return name().replace("_", " ").toLowerCase();
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}