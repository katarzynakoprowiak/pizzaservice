package com.pizzaservice.pizza;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CapriciosaTest {

    @Test
    void shouldHaveCrust(){
        //given
        Capriciosa capriciosa;

        //when
        capriciosa = new Capriciosa.Builder().build();

        //then
        assertEquals(Ingredient.CRUST, capriciosa.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Capriciosa capriciosa;

        //when
        capriciosa = new Capriciosa.Builder().build();

        //then
        assertEquals(TOMATO_SAUCE, capriciosa.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseHamMushroomsAndArtichokesToppings(){
        //given
        Capriciosa capriciosa;

        //when
        capriciosa = new Capriciosa.Builder().build();

        //then
        assertThat(capriciosa.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, HAM, MUSHROOMS, ARTICHOKES));
    }

}