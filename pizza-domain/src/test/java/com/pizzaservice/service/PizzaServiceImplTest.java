package com.pizzaservice.service;

import com.pizzaservice.pizza.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.pizzaservice.service.PaymentMethod.CASH;
import static com.pizzaservice.service.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PizzaServiceImplTest {
    PizzaServiceImpl pizzaService;
    PizzaFactory mockFactory;

    @BeforeEach
    void setup(){
        mockFactory = mock(PizzaFactory.class);
        pizzaService = new PizzaServiceImpl(mockFactory);
    }

    @Test
    void shouldReturnPizzaListBasedOnGivenOrder(){
        //given
        when(mockFactory.makePizza(PizzaType.MARGHERITA)).thenReturn(new Margherita.Builder().build());
        when(mockFactory.makePizza(PizzaType.FUNGHI)).thenReturn(new Funghi.Builder().build());
        when(mockFactory.makePizza(PizzaType.CAPRICIOSA)).thenReturn(new Capriciosa.Builder().build());

        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(PizzaType.MARGHERITA,
                PizzaType.FUNGHI, PizzaType.CAPRICIOSA));

        //when
        List<Pizza> pizzas = pizzaService.makePizza(mockOrder);

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
        when(mockFactory.makePizza(PizzaType.CALZONE)).thenReturn(new Calzone.Builder().build());

        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(PizzaType.CALZONE,
                PizzaType.CALZONE));

        //when
        List<Pizza> pizzas = pizzaService.makePizza(mockOrder);

        //then
        assertThat(pizzas,
                contains(new Calzone.Builder().build(), new Calzone.Builder().build()));
    }
}