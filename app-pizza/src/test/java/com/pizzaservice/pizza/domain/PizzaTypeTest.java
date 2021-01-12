package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.pizzaservice.pizza.domain.PizzaType.*;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PizzaTypeTest {
    @Test
    void shouldReturnMargheritaWhenMargheritaStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("margherita");

        //then
        assertEquals(MARGHERITA, pizzaType);
    }

    @Test
    void shouldReturnCapriciosaWhenCapriciosaStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("capriciosa");

        //then
        assertEquals(CAPRICIOSA, pizzaType);
    }

    @Test
    void shouldReturnFunghiWhenFunghiStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("funghi");

        //then
        assertEquals(FUNGHI, pizzaType);
    }

    @Test
    void shouldReturnCalzonaWhenCalzoneStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("calzone");

        //then
        assertEquals(CALZONE, pizzaType);
    }

    @Test
    void shouldThrowExceptionWhenIllegalTypeStringInput(){
        //when & then
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> PizzaType.getByString("Invalid pizza type"));

        //then
        String expected = "Pizza of type Invalid pizza type is unavailable.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void shouldPrintPizzaTypeInTitleCase(){
        //given
        List<PizzaType> pizzaTypes = Arrays.asList(values());

        //when
        List<String> pizzaTypesToSting = pizzaTypes.stream()
                .map(PizzaType::toString)
                .collect(toList());

        //then
        assertThat(pizzaTypesToSting, containsInAnyOrder("Margherita", "Capriciosa", "Funghi", "Calzone"));
    }
}