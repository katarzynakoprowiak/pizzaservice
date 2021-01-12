package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.domain.IngredientType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class CapriciosaTest {

    @Test
    void shouldHaveCrust(){
        //given
        Capriciosa capriciosa;

        //when
        capriciosa = Capriciosa.builder().build();

        //then
        assertEquals(new Ingredient(CRUST), capriciosa.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Capriciosa capriciosa;

        //when
        capriciosa = Capriciosa.builder().build();

        //then
        assertEquals(new Ingredient(TOMATO_SAUCE), capriciosa.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseHamMushroomsAndArtichokesToppings(){
        //given
        Capriciosa capriciosa;

        //when
        capriciosa = Capriciosa.builder().build();

        //then
        assertThat(capriciosa.getToppings(),
                containsInAnyOrder(
                        new Ingredient(CHEESE),
                        new Ingredient(HAM, 2),
                        new Ingredient(MUSHROOMS),
                        new Ingredient(ARTICHOKES)));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Capriciosa capriciosa = Capriciosa.builder().build();

        //when
        String capriciosaToString = capriciosa.toString();

        //then
        String expected = "Capriciosa - crust, tomato sauce, cheese, double ham, mushrooms, artichokes";
        assertEquals(expected, capriciosaToString);
    }

}