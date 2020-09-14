package com.pizzaservice.service;

import com.pizzaservice.pizza.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PizzaFactoryTest {
    private static final PizzaFactory FACTORY = new PizzaFactory();
    private static final String MARGHERITA = "Margherita";
    private static final String CAPRICIOSA = "Capriciosa";
    private static final String FUNGHI = "Funghi";
    private static final String CALZONE = "Calzone";
    private static final Ingredient CRUST = new Ingredient("crust");
    private static final Ingredient ROLLED_CRUST = new Ingredient("rolled crust");
    private static final Ingredient TOMATO_SAUCE = new Ingredient("tomato sauce");
    private static final Ingredient CHEESE = new Ingredient("cheese");
    private static final Ingredient HAM = new Ingredient("ham");
    private static final Ingredient MUSHROOMS = new Ingredient("mushrooms");
    private static final Ingredient ARTICHOKES = new Ingredient("artichokes");
    private static final Ingredient BELL_PEPPER = new Ingredient("bell pepper");
    private static final Ingredient ONION = new Ingredient("onion");

    @Test
    void shouldMakeMargheritaWhenRequested(){
        Pizza margherita = FACTORY.makePizza(MARGHERITA);
        assertEquals(MARGHERITA, margherita.getName());
        assertEquals(CRUST, margherita.getCrust());
        assertEquals(TOMATO_SAUCE, margherita.getSauce());
        assertThat(margherita.getToppings(),
                Matchers.containsInAnyOrder(CHEESE));
    }

    @Test
    void shouldMakeCapriciosaWhenRequested(){
        Pizza capriciosa = FACTORY.makePizza(CAPRICIOSA);
        assertEquals(CAPRICIOSA, capriciosa.getName());
        assertEquals(CRUST, capriciosa.getCrust());
        assertEquals(TOMATO_SAUCE, capriciosa.getSauce());
        assertThat(capriciosa.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, HAM, MUSHROOMS, ARTICHOKES));
    }

    @Test
    void shouldMakeFunghiWhenRequested(){
        Pizza funghi = FACTORY.makePizza(FUNGHI);
        assertEquals(FUNGHI, funghi.getName());
        assertEquals(CRUST, funghi.getCrust());
        assertEquals(TOMATO_SAUCE, funghi.getSauce());
        assertThat(funghi.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, MUSHROOMS));

    }

    @Test
    void shouldMakeCalzoneWhenRequested(){
        Pizza calzone = FACTORY.makePizza(CALZONE);
        assertEquals(CALZONE, calzone.getName());
        assertEquals(ROLLED_CRUST, calzone.getCrust());
        assertEquals(TOMATO_SAUCE, calzone.getSauce());
        assertThat(calzone.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, HAM, BELL_PEPPER, ONION));
    }

    @Test
    void shouldThrowExceptionWhenIllegalPizzaTypeRequested(){
        assertThrows(IllegalArgumentException.class,
                () -> FACTORY.makePizza("Illegal pizza type"));
    }

    @Test
    void shouldPrintAvailablePizzaTypes(){
        StringBuilder expectedMenu = new StringBuilder();
        expectedMenu.append("Margherita - crust, tomato sauce, cheese\n");
        expectedMenu.append("Capriciosa - crust, tomato sauce, cheese, ham, mushrooms, artichokes\n");
        expectedMenu.append("Funghi - crust, tomato sauce, cheese, mushrooms\n");
        expectedMenu.append("Calzone - rolled crust, tomato sauce, cheese, ham, bell pepper, onion\n");

        assertEquals(expectedMenu.toString(), FACTORY.printMenu());
    }

}