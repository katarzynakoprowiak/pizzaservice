package com.pizzaservice.pizza;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class FunghiTest {
    @Test
    void shouldHaveCrust(){
        //given
        Funghi funghi;

        //when
        funghi = new Funghi.Builder().build();

        //then
        assertEquals(CRUST, funghi.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Funghi funghi;

        //when
        funghi = new Funghi.Builder().build();

        //then
        assertEquals(TOMATO_SAUCE, funghi.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseMushroomsToppings(){
        //given
        Funghi funghi;

        //when
        funghi = new Funghi.Builder().build();

        //then
        assertThat(funghi.getToppings(),
                containsInAnyOrder(CHEESE, MUSHROOMS));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Funghi funghi = new Funghi.Builder().build();

        //when
        String funghiToString = funghi.toString();

        //then
        String expected = "Funghi - crust, tomato sauce, cheese, mushrooms";
        assertEquals(expected, funghiToString);
    }
}