package com.pizzaservice.pizza;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static org.hamcrest.MatcherAssert.assertThat;
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
                Matchers.containsInAnyOrder(CHEESE, HAM, BELL_PEPPER, ONION));
    }

//    @Test TODO
//    void shouldCorrectlyPrintItself(){
//
//    }

}