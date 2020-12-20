package com.pizzaservice.pricecalculator;

import com.pizzaservice.pizza.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PizzaPriceCalculatorImplTest {
    PizzaPriceCalculatorImpl calculator = new PizzaPriceCalculatorImpl();

    @Test
    void shouldCalculatePizzaPrice(){
        //given
        Pizza margherita = new Margherita.Builder().build();
        Pizza capriciosa = new Capriciosa.Builder().build();
        Pizza funghi = new Funghi.Builder().build();
        Pizza calzone = new Calzone.Builder().build();

        //when
        double margeritaPrice = calculator.calculatePrice(margherita, 1);
        double capriciosaPrice = calculator.calculatePrice(capriciosa, 1);
        double funghiPrice = calculator.calculatePrice(funghi, 1);
        double calzonePrice = calculator.calculatePrice(calzone, 1);

        //then
        assertEquals(9.74, margeritaPrice);
        assertEquals(18.94, capriciosaPrice);
        assertEquals(13.29, funghiPrice);
        assertEquals(13.82, calzonePrice);
    }

    @Test
    void shouldApplyPriceModifierToPizzaPrice(){
        Pizza margherita = new Margherita.Builder().build();
        Pizza capriciosa = new Capriciosa.Builder().build();
        Pizza funghi = new Funghi.Builder().build();
        Pizza calzone = new Calzone.Builder().build();

        //when
        double margeritaPrice = calculator.calculatePrice(margherita, 1.1);
        double capriciosaPrice = calculator.calculatePrice(capriciosa, 1.25);
        double funghiPrice = calculator.calculatePrice(funghi, 0.8);
        double calzonePrice = calculator.calculatePrice(calzone, 0.6);

        //then
        assertEquals(10.714, margeritaPrice);
        assertEquals(23.675, capriciosaPrice);
        assertEquals(10.632, funghiPrice);
        assertEquals(8.292, calzonePrice);
    }

}