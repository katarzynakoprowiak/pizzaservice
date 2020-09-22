package com.pizzaservice.service;


import com.pizzaservice.pizza.Capriciosa;
import com.pizzaservice.pizza.Funghi;
import com.pizzaservice.pizza.Margherita;
import com.pizzaservice.pizza.Pizza;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

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
                Matchers.containsInAnyOrder(
                        new Margherita.Builder().build(),
                        new Funghi.Builder().build(),
                        new Capriciosa.Builder().build()));
    }

    @Test
    @Disabled
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

    }

}