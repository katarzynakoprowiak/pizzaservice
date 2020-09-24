package com.pizzaservice.pizza;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class CalzoneTest {
    @Test
    void shouldHaveRolledCrust(){
        //given
        Calzone calzone;

        //when
        calzone = new Calzone.Builder().build();

        //then
        assertEquals(ROLLED_CRUST, calzone.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Calzone calzone;

        //when
        calzone = new Calzone.Builder().build();

        //then
        assertEquals(TOMATO_SAUCE, calzone.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseHamPepperAndOnionToppings(){
        //given
        Calzone calzone;

        //when
        calzone = new Calzone.Builder().build();

        //then
        assertThat(calzone.getToppings(),
                containsInAnyOrder(CHEESE, HAM, BELL_PEPPER, ONION));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Calzone calzone = new Calzone.Builder().build();

        //when
        String calzoneToString = calzone.toString();

        //then
        String expected = "Calzone - rolled crust, tomato sauce, cheese, ham, bell pepper, onion";
        assertEquals(expected, calzoneToString);
    }

}