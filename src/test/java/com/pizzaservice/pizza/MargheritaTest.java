package com.pizzaservice.pizza;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MargheritaTest {
    @Test
    void shouldHaveCrust(){
        //given
        Margherita margherita;

        //when
        margherita = new Margherita.Builder().build();

        //then
        assertEquals(CRUST, margherita.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Margherita margherita;

        //when
        margherita = new Margherita.Builder().build();

        //then
        assertEquals(TOMATO_SAUCE, margherita.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseTopping(){
        //given
        Margherita margherita;

        //when
        margherita = new Margherita.Builder().build();

        //then
        assertThat(margherita.getToppings(),
                Matchers.contains(CHEESE));
    }
}