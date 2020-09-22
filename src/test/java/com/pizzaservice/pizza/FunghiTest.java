package com.pizzaservice.pizza;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static com.pizzaservice.pizza.Ingredient.ARTICHOKES;
import static org.hamcrest.MatcherAssert.assertThat;
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
                Matchers.containsInAnyOrder(CHEESE, MUSHROOMS));
    }
}