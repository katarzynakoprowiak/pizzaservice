package com.pizzaservice.pizza;

import com.pizzaservice.service.PaymentMethod;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PizzaTypeTest {
    @Test
    void shouldReturnMargheritaWhenMargheritaStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("margherita");

        //then
        assertEquals(PizzaType.MARGHERITA, pizzaType);
    }

    @Test
    void shouldReturnCapriciosaWhenCapriciosaStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("capriciosa");

        //then
        assertEquals(PizzaType.CAPRICIOSA, pizzaType);
    }

    @Test
    void shouldReturnFunghiWhenFunghiStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("funghi");

        //then
        assertEquals(PizzaType.FUNGHI, pizzaType);
    }

    @Test
    void shouldReturnCalzonaWhenCalzoneStringInput(){
        //given
        PizzaType pizzaType;

        //when
        pizzaType = PizzaType.getByString("calzone");

        //then
        assertEquals(PizzaType.CALZONE, pizzaType);
    }

    @Test
    void shouldThrowExceptionWhenIllegalTypeStringInput(){
        //when & then
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> PaymentMethod.getByString("Invalid payment method"));

        //then
        String expected = "Payment option of type Invalid payment method is not available." +
                " Please select other payment type.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    @Disabled
    void shouldPrintPizzaTypeInTitleCase(){
        //given
        PizzaType margherita = PizzaType.MARGHERITA;
        PizzaType capriciosa = PizzaType.CAPRICIOSA;
        PizzaType funghi = PizzaType.FUNGHI;
        PizzaType calzone = PizzaType.CALZONE;

        //when
        String margheritaToString = margherita.toString();
        String capriciosaToString = capriciosa.toString();
        String funghiToString = funghi.toString();
        String calzoneToString = calzone.toString();

        //then
        assertEquals("Margherita", margheritaToString);
        assertEquals("Capriciosa", capriciosaToString);
        assertEquals("Funghi", funghiToString);
        assertEquals("Calzone", calzoneToString);
    }
}