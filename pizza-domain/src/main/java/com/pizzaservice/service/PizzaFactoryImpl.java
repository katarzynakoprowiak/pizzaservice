package com.pizzaservice.service;

import com.pizzaservice.pizza.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pizzaservice.pizza.PizzaType.*;

@Component("pizzaFactory")
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
        if(MARGHERITA.equals(pizzaType)){
            return new Margherita.Builder().build();
        }
        if(CAPRICIOSA.equals(pizzaType)){
            return new Capriciosa.Builder().build();
        }
        if(FUNGHI.equals(pizzaType)){
            return new Funghi.Builder().build();
        }
        if(CALZONE.equals(pizzaType)) {
            return new Calzone.Builder().build();
        }
        throw new IllegalArgumentException(
                String.format("Pizza of type %s is unavailable.", pizzaType));
    }

    public List<Pizza> getMenu(){
        return menu;
    }
}
