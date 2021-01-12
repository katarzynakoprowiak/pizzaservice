package com.pizzaservice.pizza.application;

import com.pizzaservice.pizza.application.PizzaPriceCalculatorImpl;
import com.pizzaservice.pizza.domain.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

class PizzaPriceCalculatorImplTest {
    PizzaPriceCalculatorImpl calculator = new PizzaPriceCalculatorImpl();

    @Test
    void shouldCalculatePizzaPrice(){
        //given
        Pizza margherita = Margherita.builder().build();
        Pizza capriciosa = Capriciosa.builder().build();
        Pizza funghi = Funghi.builder().build();
        Pizza calzone = Calzone.builder().build();

        //when
        BigDecimal margeritaPrice = calculator.calculatePrice(margherita, 1);
        BigDecimal capriciosaPrice = calculator.calculatePrice(capriciosa, 1);
        BigDecimal funghiPrice = calculator.calculatePrice(funghi, 1);
        BigDecimal calzonePrice = calculator.calculatePrice(calzone, 1);

        //then
        assertThat(margeritaPrice, comparesEqualTo(BigDecimal.valueOf(9.74)));
        assertThat(capriciosaPrice, comparesEqualTo(BigDecimal.valueOf(18.84)));
        assertThat(funghiPrice, comparesEqualTo(BigDecimal.valueOf(12.99)));
        assertThat(calzonePrice, comparesEqualTo(BigDecimal.valueOf(13.82)));
    }

    @Test
    void shouldApplyPriceModifierToPizzaPrice(){
        Pizza margherita = Margherita.builder().build();
        Pizza capriciosa = Capriciosa.builder().build();
        Pizza funghi = Funghi.builder().build();
        Pizza calzone = Calzone.builder().build();

        //when
        BigDecimal margeritaPrice = calculator.calculatePrice(margherita, 1.1);
        BigDecimal capriciosaPrice = calculator.calculatePrice(capriciosa, 1.25);
        BigDecimal funghiPrice = calculator.calculatePrice(funghi, 0.8);
        BigDecimal calzonePrice = calculator.calculatePrice(calzone, 0.6);

        //then
        assertThat(margeritaPrice, comparesEqualTo(BigDecimal.valueOf(10.714)));
        assertThat(capriciosaPrice, comparesEqualTo(BigDecimal.valueOf(23.55)));
        assertThat(funghiPrice, comparesEqualTo(BigDecimal.valueOf(10.392)));
        assertThat(calzonePrice, comparesEqualTo(BigDecimal.valueOf(8.292)));
    }

}