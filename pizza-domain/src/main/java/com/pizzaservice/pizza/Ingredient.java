package com.pizzaservice.pizza;

import java.util.Objects;

import static com.pizzaservice.utils.PizzaUtils.numberToMultiplicationDescription;

public class Ingredient {
    private final IngredientType type;
    private final int numberOfPortions;

    public Ingredient (IngredientType type, int numberOfPortions){
        this.type = type;
        this.numberOfPortions = numberOfPortions;
    }

    public Ingredient (IngredientType type){
        this(type, 1);
    }

    public IngredientType getType() {
        return type;
    }

    @Override
    public String toString(){
        return String.format(
                numberToMultiplicationDescription(numberOfPortions), type.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return numberOfPortions == that.numberOfPortions &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, numberOfPortions);
    }
}
