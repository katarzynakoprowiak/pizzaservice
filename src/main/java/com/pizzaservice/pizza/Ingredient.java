package com.pizzaservice.pizza;

public enum Ingredient {
    CRUST("crust"),
    ROLLED_CRUST("rolled crust"),
    TOMATO_SAUCE("tomato sauce"),
    CHEESE("cheese"),
    HAM("ham"),
    MUSHROOMS("mushrooms"),
    ARTICHOKES("artichokes"),
    BELL_PEPPER("bell pepper"),
    ONION("onion");

    private String name;

    Ingredient(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
