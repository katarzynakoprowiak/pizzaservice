package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.pizzaservice.pizza.domain.PizzaType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PizzaServiceImplTest {
    private PizzaServiceImpl pizzaService;
    private PizzaFactory mockFactory;

    @BeforeEach
    void setup(){
        mockFactory = mock(PizzaFactory.class);
        pizzaService = new PizzaServiceImpl(mockFactory);
    }

    @Test
    void shouldReturnPizzaListBasedOnGivenOrder(){
        //given
        when(mockFactory.makePizza(MARGHERITA)).thenReturn(Margherita.builder().build());
        when(mockFactory.makePizza(FUNGHI)).thenReturn(Funghi.builder().build());
        when(mockFactory.makePizza(CAPRICIOSA)).thenReturn(Capriciosa.builder().build());

        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(MARGHERITA,
                FUNGHI, CAPRICIOSA));

        //when
        List<Pizza> pizzas = pizzaService.makePizza(mockOrder);

        //then
        assertThat(pizzas, containsInAnyOrder(
                        Margherita.builder().build(),
                        Funghi.builder().build(),
                        Capriciosa.builder().build()));
    }

    @Test
    void shouldReturnAsManyPizzasOfGivenTypeAsOrdered(){
        //given
        when(mockFactory.makePizza(CALZONE)).thenReturn(Calzone.builder().build());

        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(CALZONE,
                CALZONE));

        //when
        List<Pizza> pizzas = pizzaService.makePizza(mockOrder);

        //then
        assertThat(pizzas,
                contains(Calzone.builder().build(), Calzone.builder().build()));
    }
}