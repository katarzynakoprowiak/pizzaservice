package com.pizzaservice.pizza;

public enum IngredientType {
    CRUST(240),
    ROLLED_CRUST(200),
    TOMATO_SAUCE(50),
    CHEESE(100),
    HAM(40),
    MUSHROOMS(25),
    ARTICHOKES(20),
    BELL_PEPPER(20),
    ONION(20);

    private final int weight;

    IngredientType(int weight){
        this.weight = weight;
    }

    @Override
    public String toString(){
        return name().replace("_", " ").toLowerCase();
    }

    public int getWeight() {
        return weight;
    }
}
