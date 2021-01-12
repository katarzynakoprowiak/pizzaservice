package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.pizzaservice.pizza.domain.PaymentMethod.CASH;
import static com.pizzaservice.pizza.domain.PizzaType.FUNGHI;
import static com.pizzaservice.pizza.domain.PizzaType.MARGHERITA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;
    private static final Long USER_ID = 1L;

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
        order.setId(12L);
        String expectedString = "Order #12 ";

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
        order.setId(12L);
        order.setUserId(USER_ID);

        //when
        String orderToString = order.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Order #12 placed by 1:\n");
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