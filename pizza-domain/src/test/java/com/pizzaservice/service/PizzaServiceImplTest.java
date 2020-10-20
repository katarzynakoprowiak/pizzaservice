package com.pizzaservice.service;

import com.pizzaservice.pizza.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.pizzaservice.service.PaymentMethod.CASH;
import static com.pizzaservice.service.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PizzaServiceImplTest {

    @Test
    void shouldReturnPizzaListBasedOnGivenOrder(){
        //given
        PizzaServiceImpl pizzaService = new PizzaServiceImpl(new PizzaFactoryImpl());
        Order order = new Order();
        order.addItems(Arrays.asList(
                "margherita",
                "funghi",
                "capriciosa"));
        order.setPaymentMethod(CREDIT_CARD);

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
        Order order = new Order();
        order.addItems(Arrays.asList(
                "calzone",
                "calzone"));
        order.setPaymentMethod(CASH);

        //when
        List<Pizza> pizzas = pizzaService.makePizza(order);

        //then
        assertThat(pizzas,
                contains(new Calzone.Builder().build(), new Calzone.Builder().build()));
    }
}