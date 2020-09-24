package com.pizzaservice.service;

import com.pizzaservice.pizza.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PizzaFactoryImpl implements PizzaFactory{
    private List<Pizza> menu = Stream.of(PizzaType.values())
            .map(p -> makePizza(p))
            .collect(Collectors.toList());

    public PizzaFactoryImpl(){}

    public Pizza makePizza(String type){
        PizzaType pizzaType = PizzaType.getByString(type);
        return makePizza(pizzaType);
    }

    @Override
    public Pizza makePizza(PizzaType pizzaType){
        if(PizzaType.MARGHERITA.equals(pizzaType)){
            return new Margherita.Builder().build();
        }
        if(PizzaType.CAPRICIOSA.equals(pizzaType)){
            return new Capriciosa.Builder().build();
        }
        if(PizzaType.FUNGHI.equals(pizzaType)){
            return new Funghi.Builder().build();
        }
        if(PizzaType.CALZONE.equals(pizzaType)) {
            return new Calzone.Builder().build();
        }
        throw new IllegalArgumentException(
                String.format("Pizza of type %s is unavailable.", pizzaType));
    }

    public String printMenu(){
        List<String> stringMenu = menu.stream()
                .map(Pizza::toString)
                .collect(Collectors.toList());

        return String.join("\n", stringMenu);
    }
}
