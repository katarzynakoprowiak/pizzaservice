package com.pizzaservice.service;

import com.pizzaservice.pizza.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaServiceImplTest {

    @Test
    void shouldReturnPizzaListBasedOnGivenOrder(){
        //given
        PizzaServiceImpl pizzaService = new PizzaServiceImpl(new PizzaFactoryImpl());
        Order order = new Order.Builder()
                .addItem("margherita")
                .addItem("funghi")
                .addItem("capriciosa")
                .paymentMethod("card").build();

        //when
        List<Pizza> pizzas = pizzaService.makePizza(order);

        //then
        assertThat(pizzas,
                containsInAnyOrder(
                        new Margherita.Builder().build(),
                        new Funghi.Builder().build(),
                        new Capriciosa.Builder().build()));
    }

    @Test
    void shouldReturnAsManyPizzasOfGivenTypeAsOrdered(){
        //given
        PizzaServiceImpl pizzaService = new PizzaServiceImpl(new PizzaFactoryImpl());
        Order order = new Order.Builder()
                .addItem("calzone")
                .addItem("calzone")
                .paymentMethod("cash").build();

        //when
        List<Pizza> pizzas = pizzaService.makePizza(order);

        //then
        assertThat(pizzas,
                contains(new Calzone.Builder().build(), new Calzone.Builder().build()));
    }
}