package com.pizzaservice.pizza.domain;

import com.pizzaservice.pizza.domain.Ingredient;
import com.pizzaservice.pizza.domain.Pizza;
import com.pizzaservice.pizza.domain.PizzaFactoryImpl;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.domain.PizzaType.*;
import static com.pizzaservice.pizza.domain.IngredientType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PizzaFactoryImplTest {
    //given
    private PizzaFactoryImpl factory = new PizzaFactoryImpl();

    @Test
    void shouldMakeMargheritaWhenRequested(){
        //when
        Pizza margherita = factory.makePizza("margherita");

        //then
        assertEquals(MARGHERITA, margherita.getPizzaType());
        assertEquals(new Ingredient(CRUST), margherita.getCrust());
        assertEquals(new Ingredient(TOMATO_SAUCE, 2), margherita.getSauce());
        assertThat(margherita.getToppings(),
                containsInAnyOrder(
                        new Ingredient(CHEESE, 2)));
    }

    @Test
    void shouldMakeCapriciosaWhenRequested(){
        //when
        Pizza capriciosa = factory.makePizza("capriciosa");

        //then
        assertEquals(CAPRICIOSA, capriciosa.getPizzaType());
        assertEquals(new Ingredient(CRUST), capriciosa.getCrust());
        assertEquals(new Ingredient(TOMATO_SAUCE), capriciosa.getSauce());
        assertThat(capriciosa.getToppings(),
                containsInAnyOrder(
                        new Ingredient(CHEESE),
                        new Ingredient(HAM, 2),
                        new Ingredient(MUSHROOMS),
                        new Ingredient(ARTICHOKES)));
    }

    @Test
    void shouldMakeFunghiWhenRequested(){
        //when
        Pizza funghi = factory.makePizza("funghi");

        //then
        assertEquals(FUNGHI, funghi.getPizzaType());
        assertEquals(new Ingredient(CRUST), funghi.getCrust());
        assertEquals(new Ingredient(TOMATO_SAUCE), funghi.getSauce());
        assertThat(funghi.getToppings(),
                containsInAnyOrder(
                        new Ingredient(CHEESE, 2),
                        new Ingredient(MUSHROOMS, 3)));
    }

    @Test
    void shouldMakeCalzoneWhenRequested(){
        //when
        Pizza calzone = factory.makePizza("calzone");

        //then
        assertEquals(CALZONE, calzone.getPizzaType());
        assertEquals(new Ingredient(ROLLED_CRUST), calzone.getCrust());
        assertEquals(new Ingredient(TOMATO_SAUCE), calzone.getSauce());
        assertThat(calzone.getToppings(),
                containsInAnyOrder(
                        new Ingredient(CHEESE),
                        new Ingredient(HAM, 2),
                        new Ingredient(BELL_PEPPER),
                        new Ingredient(ONION)));
    }

    @Test
    void shouldThrowExceptionWhenIllegalPizzaTypeRequested(){
        //when & then
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> factory.makePizza("Illegal pizza type"));

        //then
        String expected = "Pizza of type Illegal pizza type is unavailable.";
        assertEquals(expected, exception.getMessage());
    }
}