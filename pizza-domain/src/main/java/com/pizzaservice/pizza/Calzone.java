package com.pizzaservice.pizza;

import com.pizzaservice.model.PizzaType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Calzone implements Pizza {
    private final PizzaType pizzaType;
    private final Ingredient crust;
    private final Ingredient sauce;
    private final List<Ingredient> toppings;

    private Calzone(Builder builder){
        pizzaType = PizzaType.CALZONE;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append(pizzaType);
        builder.append(" - ");

        builder.append(crust);
        builder.append(", ");
        builder.append(sauce);

        for (Ingredient topping: toppings){
            builder.append(", ");
            builder.append(topping);
        }

        return builder.toString();
    }

    @Override
    public PizzaType getPizzaType() {
        return pizzaType;
    }

    @Override
    public Ingredient getCrust() {
        return crust;
    }

    @Override
    public Ingredient getSauce() {
        return sauce;
    }

    @Override
    public List<Ingredient> getToppings() {
        return toppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calzone calzone = (Calzone) o;
        return Objects.equals(pizzaType, calzone.pizzaType) &&
                Objects.equals(crust, calzone.crust) &&
                Objects.equals(sauce, calzone.sauce) &&
                Objects.equals(toppings, calzone.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaType, crust, sauce, toppings);
    }

    public static class Builder {
        private Ingredient crust;
        private Ingredient sauce;
        private List<Ingredient> toppings;

        public Builder(){
        }

        public Calzone build(){
            withCrust();
            withSauce();
            withToppings();
            return new Calzone(this);
        }

        private Builder withCrust(){
            this.crust = new Ingredient(IngredientType.ROLLED_CRUST);
            return this;
        }

        private Builder withSauce(){
            this.sauce = new Ingredient(IngredientType.TOMATO_SAUCE);
            return this;
        }

        private Builder withToppings(){
            toppings = Arrays.asList(
                    new Ingredient(IngredientType.CHEESE),
                    new Ingredient(IngredientType.HAM, 2),
                    new Ingredient(IngredientType.BELL_PEPPER),
                    new Ingredient(IngredientType.ONION));
            return this;
        }
    }
}
