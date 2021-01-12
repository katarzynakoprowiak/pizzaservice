package com.pizzaservice.pizza.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pizzaservice.pizza.domain.PizzaType.*;

@Component
class PizzaFactoryImpl implements PizzaFactory{
    private final List<Pizza> menu = Stream.of(PizzaType.values())
            .map(this::makePizza)
            .collect(Collectors.toList());

    public PizzaFactoryImpl(){}

    public Pizza makePizza(String type){
        PizzaType pizzaType = PizzaType.getByString(type);
        return makePizza(pizzaType);
    }

    @Override
    public Pizza makePizza(PizzaType pizzaType){
        if(MARGHERITA.equals(pizzaType)){
            return Margherita.builder().build();
        }
        if(CAPRICIOSA.equals(pizzaType)){
            return Capriciosa.builder().build();
        }
        if(FUNGHI.equals(pizzaType)){
            return Funghi.builder().build();
        }
        if(CALZONE.equals(pizzaType)) {
            return Calzone.builder().build();
        }
        throw new IllegalArgumentException(
                String.format("Pizza of type %s is unavailable.", pizzaType));
    }

    public List<Pizza> getMenu(){
        return menu;
    }
}
