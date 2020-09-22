package com.pizzaservice.service;

import com.pizzaservice.pizza.Funghi;
import com.pizzaservice.pizza.Margherita;
import com.pizzaservice.pizza.Pizza;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.pizzaservice.pizza.PizzaType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;

    @Test
    void shouldAddItems(){
        //given & when
        order = new Order.Builder()
                .addItem("margherita")
                .addItem("funghi")
                .paymentMethod("card")
                .build();

        //then
        assertThat(order.getOrderedItems(), Matchers.containsInAnyOrder(MARGHERITA, FUNGHI));
    }

    @Test
    void shouldAddPaymentMethod(){
        //given & when
        order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //then
        assertEquals(PaymentMethod.CASH, order.getPaymentMethod());
    }

    @Test
    void shouldThrowExceptionIfInvalidPaymentTypeSelected(){
        //when & then
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Order.Builder().paymentMethod("some invalid payment").build());

        //then
        String expected = "Payment option of type some invalid payment is not available." +
                " Please select other payment type.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void shouldAddCommentIfInput(){
        //given
        Order order;

        //when
        order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .comment("comment input")
                .build();

        assertEquals("comment input", order.getComment());
    }

    @Test
    void shouldPrintOrderNumberIfSet(){
        //given
        Order order;
        order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //when
        order.setOrderNumber(12);
        String expectedString = "Order #12:";

        //then
        assertTrue(order.toString().startsWith(expectedString));
    }

    @Test
    void shouldPrintSummedUpOrderedItems(){
        //given
        Order order;
        order = new Order.Builder()
                .addItem("funghi")
                .addItem("calzone")
                .addItem("funghi")
                .paymentMethod("cash")
                .build();
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
        Order order;
        order = new Order.Builder()
                .addItem("funghi")
                .paymentMethod("cash")
                .comment("Some comment")
                .build();

        //when
        String orderToString = order.toString();
        String expectedEnding = "Comment: Some comment";

        //then
        assertTrue(orderToString.endsWith(expectedEnding));
    }

    @Test
    void shouldNotPrintCommentIfEmpty(){
        //given
        Order order;
        order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //when
        String orderToString = order.toString();
        String expectedEnding = "Payment method: cash\n";

        assertTrue(orderToString.endsWith(expectedEnding));
    }

    @Test
    void shouldThrowExceptionWhenEmptyOrderIsBuild(){
        //when & then
        Exception exception = assertThrows(IllegalStateException.class,
                () -> new Order.Builder().paymentMethod("cash").build());

        //then
        String expected = "There are no items on the order." +
                " Please select items to order and try again.";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoPaymentMethodIsSet(){
        //when & then
        Exception exception = assertThrows(IllegalStateException.class,
                () -> new Order.Builder().addItem("calzone").build());

        //then
        String expected = "No payment method was set.";
        assertEquals(expected, exception.getMessage());
    }
}