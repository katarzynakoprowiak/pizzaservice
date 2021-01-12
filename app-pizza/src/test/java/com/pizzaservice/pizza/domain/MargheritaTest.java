package com.pizzaservice.pizza.domain;

import com.pizzaservice.pizza.domain.Ingredient;
import com.pizzaservice.pizza.domain.Margherita;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.domain.IngredientType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class MargheritaTest {
    @Test
    void shouldHaveCrust(){
        //given
        Margherita margherita;

        //when
        margherita = Margherita.builder().build();

        //then
        assertEquals(new Ingredient(CRUST), margherita.getCrust());
    }

    @Test
    void shouldHaveTomatoSauce(){
        //given
        Margherita margherita;

        //when
        margherita = Margherita.builder().build();

        //then
        assertEquals(new Ingredient(TOMATO_SAUCE,2), margherita.getSauce());
    }

    @Test
    void shouldOnlyHaveCheeseTopping(){
        //given
        Margherita margherita;

        //when
        margherita = Margherita.builder().build();

        //then
        assertThat(margherita.getToppings(),
                contains(new Ingredient(CHEESE, 2)));
    }

    @Test
    void shouldCorrectlyPrintItself(){
        //given
        Margherita margherita = Margherita.builder().build();

        //when
        String margheritaToString = margherita.toString();

        //then
        String expected = "Margherita - crust, double tomato sauce, double cheese";
        assertEquals(expected, margheritaToString);
    }
}