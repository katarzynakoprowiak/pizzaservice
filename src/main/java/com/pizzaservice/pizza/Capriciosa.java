package com.pizzaservice.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Capriciosa implements Pizza {
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
            return new Capriciosa(this);
        }

        private Builder crust(){
            this.crust = new Ingredient("crust");
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
            toppings.add(new Ingredient("mushrooms"));
            toppings.add(new Ingredient("artichokes"));
            return this;
        }
    }

    private final String name;
    private final Ingredient crust;
    private final Ingredient sauce;
    private final List<Ingredient> toppings;

    private Capriciosa(Builder builder){
        name = "Capriciosa";
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
        Capriciosa that = (Capriciosa) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(crust, that.crust) &&
                Objects.equals(sauce, that.sauce) &&
                Objects.equals(toppings, that.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, crust, sauce, toppings);
    }
}
