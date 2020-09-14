package com.pizzaservice.service;

import com.pizzaservice.pizza.*;

import java.util.ArrayList;
import java.util.List;

public class PizzaFactory{
    private List<Pizza> menu;
    private static final String MARGHERITA = "Margherita";
    private static final String CAPRICIOSA = "Capriciosa";
    private static final String FUNGHI = "Funghi";
    private static final String CALZONE = "Calzone";

    public PizzaFactory(){
        createMenu();
    }

    private void createMenu() {
        menu = new ArrayList<>();
        menu.add(makePizza(MARGHERITA));
        menu.add(makePizza(CAPRICIOSA));
        menu.add(makePizza(FUNGHI));
        menu.add(makePizza(CALZONE));
    }

    public Pizza makePizza(String type){
        if(type.equalsIgnoreCase(MARGHERITA)){
            return new Margherita.Builder().build();
        }
        if(type.equalsIgnoreCase(CAPRICIOSA)){
            return new Capriciosa.Builder().build();
        }
        if(type.equalsIgnoreCase(FUNGHI)){
            return new Funghi.Builder().build();
        }
        if(type.equalsIgnoreCase(CALZONE)) {
            return new Calzone.Builder().build();
        }
        throw new IllegalArgumentException("Pizza you ordered is not on the menu.");
    }

    public String printMenu(){
        StringBuilder builder = new StringBuilder();

        for (Pizza pizza: menu){
            builder.append(pizza);
            builder.append("\n");
        }

        return builder.toString();
    }
}
