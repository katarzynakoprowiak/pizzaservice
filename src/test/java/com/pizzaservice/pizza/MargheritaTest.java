package com.pizzaservice.pizza;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
                contains(CHEESE));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Margherita margherita = new Margherita.Builder().build();

        //when
        String margheritaToString = margherita.toString();

        //then
        String expected = "Margherita - crust, tomato sauce, cheese";
        assertEquals(expected, margheritaToString);
    }
}