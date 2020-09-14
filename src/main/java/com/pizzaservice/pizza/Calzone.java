package com.pizzaservice.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Calzone implements Pizza {
    public static class Builder {
        private Ingredient crust;
        private Ingredient sauce;
        private List<Ingredient> toppings;

        public Builder(){
        }

        public Pizza build(){
            crust();
            sauce();
            toppings();
            return new Calzone(this);
        }

        private Builder crust(){
            this.crust = new Ingredient("rolled crust");
            return this;
        }

        private Builder sauce(){
            this.sauce = new Ingredient("tomato sauce");
            return this;
        }

        private Builder toppings(){
            toppings = new ArrayList<>();
            toppings.add(new Ingredient("cheese"));
            toppings.add(new Ingredient("ham"));
            toppings.add(new Ingredient("bell pepper"));
            toppings.add(new Ingredient("onion"));
            return this;
        }
    }

    private final String name;
    private final Ingredient crust;
    private final Ingredient sauce;
    private final List<Ingredient> toppings;

    private Calzone(Builder builder){
        name = "Calzone";
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append(name);
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
    public String getName() {
        return name;
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
        return Objects.equals(name, calzone.name) &&
                Objects.equals(crust, calzone.crust) &&
                Objects.equals(sauce, calzone.sauce) &&
                Objects.equals(toppings, calzone.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, crust, sauce, toppings);
    }
}
