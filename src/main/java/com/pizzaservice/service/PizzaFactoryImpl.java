package com.pizzaservice.service;

import com.pizzaservice.pizza.*;

import java.util.Arrays;
import java.util.List;

public class PizzaFactoryImpl implements PizzaFactory{
    private List<PizzaType> menu = Arrays.asList(PizzaType.values());

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

    //TODO: lambda or stream
    public String printMenu(){
        StringBuilder builder = new StringBuilder();

        for (PizzaType pizza: menu){
            builder.append(pizza);
            builder.append("\n");
        }

        return builder.toString();
    }
}
