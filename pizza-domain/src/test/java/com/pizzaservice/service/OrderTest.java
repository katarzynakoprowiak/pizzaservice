package com.pizzaservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.pizzaservice.pizza.PizzaType.*;
import static com.pizzaservice.service.PaymentMethod.CASH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;

    @BeforeEach
    void setup(){
        order = new Order();
    }

    @Test
    void shouldAddItems(){
        //when
        order.addItems(Arrays.asList(
                "margherita",
                "funghi"));

        //then
        assertThat(order.getOrderedItems(), containsInAnyOrder(MARGHERITA, FUNGHI));
    }

    @Test
    void shouldAddPaymentMethod(){
        //when
        order.setPaymentMethod(CASH);

        //then
        assertEquals(CASH, order.getPaymentMethod());
    }

    @Test
    void shouldAddCommentIfInput(){
        //given
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        //when
        order.setComment("comment input");

        //then
        assertEquals("comment input", order.getComment());
    }

    @Test
    void shouldPrintOrderNumberIfSet(){
        //given
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        //when
        order.setOrderNumber(12);
        String expectedString = "Order #12:";

        //then
        assertTrue(order.toString().startsWith(expectedString));
    }

    @Test
    void shouldPrintSummedUpOrderedItems(){
        //given
        order.addItems(Arrays.asList(
                "funghi",
                "calzone",
                "funghi"));
        order.setPaymentMethod(CASH);
        order.setOrderNumber(12);

        //when
        String orderToString = order.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Order #12:\n");
        sb.append("1 Calzone\n");
        sb.append("2 Funghis\n");
        sb.append("Payment method: cash\n");
        String expected = sb.toString();

        //then
        assertEquals(expected, orderToString);
    }

    @Test
    void shouldPrintCommentIfInput(){
        //given
        order.addItem("funghi");
        order.setPaymentMethod(CASH);
        order.setComment("Some comment");

        //when
        String orderToString = order.toString();
        String expectedEnding = "Comment: Some comment";

        //then
        assertTrue(orderToString.endsWith(expectedEnding));
    }

    @Test
    void shouldNotPrintCommentIfEmpty(){
        //given
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        //when
        String orderToString = order.toString();
        String expectedEnding = "Payment method: cash\n";

        assertTrue(orderToString.endsWith(expectedEnding));
    }
}