package com.pizzaservice.service;

import com.pizzaservice.pizza.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.Ingredient.*;
import static com.pizzaservice.pizza.PizzaType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PizzaFactoryImplTest {
    //given
    private static final PizzaFactoryImpl FACTORY = new PizzaFactoryImpl();

    @Test
    void shouldMakeMargheritaWhenRequested(){
        //when
        Pizza margherita = FACTORY.makePizza("margherita");

        //then
        assertEquals(PizzaType.MARGHERITA, margherita.getPizzaType());
        assertEquals(CRUST, margherita.getCrust());
        assertEquals(TOMATO_SAUCE, margherita.getSauce());
        assertThat(margherita.getToppings(),
                Matchers.containsInAnyOrder(CHEESE));
    }

    @Test
    void shouldMakeCapriciosaWhenRequested(){
        //when
        Pizza capriciosa = FACTORY.makePizza("capriciosa");

        //then
        assertEquals(CAPRICIOSA, capriciosa.getPizzaType());
        assertEquals(CRUST, capriciosa.getCrust());
        assertEquals(TOMATO_SAUCE, capriciosa.getSauce());
        assertThat(capriciosa.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, HAM, MUSHROOMS, ARTICHOKES));
    }

    @Test
    void shouldMakeFunghiWhenRequested(){
        //when
        Pizza funghi = FACTORY.makePizza("funghi");

        //then
        assertEquals(FUNGHI, funghi.getPizzaType());
        assertEquals(CRUST, funghi.getCrust());
        assertEquals(TOMATO_SAUCE, funghi.getSauce());
        assertThat(funghi.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, MUSHROOMS));
    }

    @Test
    void shouldMakeCalzoneWhenRequested(){
        //when
        Pizza calzone = FACTORY.makePizza("calzone");

        //then
        assertEquals(CALZONE, calzone.getPizzaType());
        assertEquals(ROLLED_CRUST, calzone.getCrust());
        assertEquals(TOMATO_SAUCE, calzone.getSauce());
        assertThat(calzone.getToppings(),
                Matchers.containsInAnyOrder(CHEESE, HAM, BELL_PEPPER, ONION));
    }

    @Test
    void shouldThrowExceptionWhenIllegalPizzaTypeRequested(){
        //when & then
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> FACTORY.makePizza("Illegal pizza type"));

        //then
        String expected = "Pizza of type Illegal pizza type is unavailable.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void shouldPrintAvailablePizzaTypes(){
        //when
        String actualMenu = FACTORY.printMenu();

        //then
        StringBuilder expectedMenu = new StringBuilder();
        expectedMenu.append("Margherita - crust, tomato sauce, cheese\n");
        expectedMenu.append("Capriciosa - crust, tomato sauce, cheese, ham, mushrooms, artichokes\n");
        expectedMenu.append("Funghi - crust, tomato sauce, cheese, mushrooms\n");
        expectedMenu.append("Calzone - rolled crust, tomato sauce, cheese, ham, bell pepper, onion\n");

        assertEquals(expectedMenu.toString(), actualMenu);
    }
}