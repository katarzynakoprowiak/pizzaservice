package com.pizzaservice.pizza;

import com.pizzaservice.pricecalculator.PriceCalculable;

public enum IngredientType implements PriceCalculable {
    CRUST(240, 4.3),
    ROLLED_CRUST(200, 4.2),
    TOMATO_SAUCE(50, 1.25),
    CHEESE(100, 1.47),
    HAM(40, 2.31),
    MUSHROOMS(25, 1.6),
    ARTICHOKES(20, 5.7),
    BELL_PEPPER(20, 2.14),
    ONION(20, 0.14);

    private final int weight;
    private final double price;

    IngredientType(int weight, double price){
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
    public double getPrice() {
        return price;
    }
}