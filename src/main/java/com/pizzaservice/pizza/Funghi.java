package com.pizzaservice.pizza;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Funghi implements Pizza {
    private final PizzaType pizzaType;
    private final Ingredient crust;
    private final Ingredient sauce;
    private final List<Ingredient> toppings;

    private Funghi(Builder builder){
        pizzaType = PizzaType.FUNGHI;
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
        Funghi funghi = (Funghi) o;
        return Objects.equals(pizzaType, funghi.pizzaType) &&
                Objects.equals(crust, funghi.crust) &&
                Objects.equals(sauce, funghi.sauce) &&
                Objects.equals(toppings, funghi.toppings);
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

        public Funghi build(){
            withCrust();
            withSauce();
            withToppings();
            return new Funghi(this);
        }

        private Builder withCrust(){
            this.crust = Ingredient.CRUST;
            return this;
        }

        private Builder withSauce(){
            this.sauce = Ingredient.TOMATO_SAUCE;
            return this;
        }

        private Builder withToppings(){
            toppings = Arrays.asList(
                    Ingredient.CHEESE,
                    Ingredient.MUSHROOMS);
            return this;
        }
    }
}
