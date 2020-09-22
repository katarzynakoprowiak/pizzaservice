package com.pizzaservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {
    @Test
    void shouldReturnCashPaymentMethodIfCashStringInput(){
        //given
        PaymentMethod method;

        //given
        method = PaymentMethod.getByString("cash");

        //then
        assertEquals(PaymentMethod.CASH, method);
    }

    @Test
    void shouldReturnCreditCardPaymentMethodIfCreditCardStringInput(){
        //given
        PaymentMethod method;

        //when
        method = PaymentMethod.getByString("credit card");

        //then
        assertEquals(PaymentMethod.CREDIT_CARD, method);
    }

    @Test
    void shouldReturnCreditCardPaymentMethodIfCardStringInput(){
        //given
        PaymentMethod method;

        //when
        method = PaymentMethod.getByString("card");

        //then
        assertEquals(PaymentMethod.CREDIT_CARD, method);
    }

    @Test
    void shouldThrowExceptionWhenIllegalPaymentMethodInput(){
        //when & then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PaymentMethod.getByString("Illegal payment method");
        });

        //then
        String expected = "Payment option of type Illegal payment method is not available." +
        " Please select other payment type.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void shouldPrintPaymentMethodInLowerCaseWithNoUnderScore(){
        //given
        PaymentMethod cash = PaymentMethod.CASH;
        PaymentMethod card = PaymentMethod.CREDIT_CARD;

        //when
        String cashToString = cash.toString();
        String cardToString = card.toString();

        //then
        assertEquals("cash", cashToString);
        assertEquals("credit card", cardToString);
    }
}