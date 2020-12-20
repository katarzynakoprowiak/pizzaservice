package com.pizzaservice.model;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.model.PaymentMethod.CASH;
import static com.pizzaservice.model.PaymentMethod.CREDIT_CARD;
import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {
    @Test
    void shouldPrintPaymentMethodInLowerCaseWithNoUnderScore(){
        //given
        PaymentMethod cash = CASH;
        PaymentMethod card = CREDIT_CARD;

        //when
        String cashToString = cash.toString();
        String cardToString = card.toString();

        //then
        assertEquals("cash", cashToString);
        assertEquals("credit card", cardToString);
    }
}