package com.pizzaservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {
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