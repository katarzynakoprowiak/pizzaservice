package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.domain.IngredientType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class CalzoneTest {
    @Test
    void shouldHaveRolledCrust(){
        //given
        Calzone calzone;

        //when
        calzone = Calzone.builder().build();

        //then
        assertEquals(new Ingredient(ROLLED_CRUST), calzone.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Calzone calzone;

        //when
        calzone = Calzone.builder().build();

        //then
        assertEquals(new Ingredient(TOMATO_SAUCE), calzone.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseHamPepperAndOnionToppings(){
        //given
        Calzone calzone;

        //when
        calzone = Calzone.builder().build();

        //then
        assertThat(calzone.getToppings(),
                containsInAnyOrder(
                                new Ingredient(CHEESE),
                                new Ingredient(HAM, 2),
                                new Ingredient(BELL_PEPPER),
                                new Ingredient(ONION)));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Calzone calzone = Calzone.builder().build();

        //when
        String calzoneToString = calzone.toString();

        //then
        String expected = "Calzone - rolled crust, tomato sauce, cheese, double ham, bell pepper, onion";
        assertEquals(expected, calzoneToString);
    }

}