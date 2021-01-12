package com.pizzaservice.pizza.domain;

import com.pizzaservice.pizza.domain.Funghi;
import com.pizzaservice.pizza.domain.Ingredient;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.domain.IngredientType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class FunghiTest {
    @Test
    void shouldHaveCrust(){
        //given
        Funghi funghi;

        //when
        funghi = Funghi.builder().build();

        //then
        assertEquals(new Ingredient(CRUST), funghi.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Funghi funghi;

        //when
        funghi = Funghi.builder().build();

        //then
        assertEquals(new Ingredient(TOMATO_SAUCE), funghi.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseMushroomsToppings(){
        //given
        Funghi funghi;

        //when
        funghi = Funghi.builder().build();

        //then
        assertThat(funghi.getToppings(),
                containsInAnyOrder(
                        new Ingredient(CHEESE, 2),
                        new Ingredient(MUSHROOMS, 3)));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Funghi funghi = Funghi.builder().build();

        //when
        String funghiToString = funghi.toString();

        //then
        String expected = "Funghi - crust, tomato sauce, double cheese, triple mushrooms";
        assertEquals(expected, funghiToString);
    }
}