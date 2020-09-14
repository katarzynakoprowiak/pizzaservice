package com.pizzaservice.service;

import com.pizzaservice.pizza.Funghi;
import com.pizzaservice.pizza.Margherita;
import com.pizzaservice.pizza.Pizza;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    private Order.Builder builder;
    private static final Pizza MARGHERITA = new Margherita.Builder().build();
    private static final Pizza FUNGHI = new Funghi.Builder().build();

    @BeforeEach
    void BeforeEach() {
        builder = new Order.Builder(new PizzaFactory());
    }

    @Test
    void shouldAddItems(){
        builder.addItem("margherita");
        builder.addItem("funghi");
        builder.paymentMethod("card");
        order = builder.build();

        assertThat(order.getOrderedItems(), Matchers.containsInAnyOrder(MARGHERITA, FUNGHI));
    }

    @Test
    void shouldAddPaymentMethod(){
        builder.addItem("margherita");
        builder.addItem("funghi");
        builder.paymentMethod("cash");
        order = builder.build();

        assertEquals(PaymentMethod.cash, order.getPaymentMethod());
    }

    @Test
    void shouldThrowErrorIfInvalidPaymentTypeSelected(){
        assertThrows(IllegalArgumentException.class,
                () -> builder.paymentMethod("some invalid payment"));
    }

    @Test
    void shouldAddCommentIfInput(){
        builder.comment("comment input");
        order = builder.build();

        assertEquals("comment input", order.getComment());
    }

    @Test
    void shouldPrintOrderNumberIfSet(){
        builder.addItem("funghi");
        builder.paymentMethod("cash");
        order = builder.build();
        order.setOrderNumber(12);
        String expectedString = "Order #12:";

        assertTrue(order.toString().startsWith(expectedString));
    }

    @Test
    void shouldPrintSummedUpOrderedItems(){
        builder.addItem("funghi");
        builder.addItem("calzone");
        builder.addItem("funghi");
        builder.paymentMethod("cash");
        order = builder.build();
        order.setOrderNumber(12);
        StringBuilder sb = new StringBuilder();
        sb.append("Order #12:\n");
        sb.append("1 Calzone\n");
        sb.append("2 Funghis\n");
        sb.append("Payment method: cash\n");
        String expected = sb.toString();

        assertEquals(expected, order.toString());
    }

    @Test
    void shouldPrintCommentIfInput(){
        builder.addItem("funghi");
        builder.paymentMethod("cash");
        builder.comment("Some comment");
        order = builder.build();
        order.setOrderNumber(12);
        String expectedEnding = "Comment: Some comment";

        assertTrue(order.toString().endsWith(expectedEnding));
    }

    @Test
    void shouldNotPrintCommentIfEmpty(){
        builder.addItem("funghi");
        builder.paymentMethod("cash");
        order = builder.build();
        order.setOrderNumber(12);
        String expectedEnding = "Payment method: cash\n";

        assertTrue(order.toString().endsWith(expectedEnding));
    }

    @Test
    @Disabled
    void shouldThrowExceptionWhenEmptyOrderIsBuild(){
    }

    @Test
    @Disabled
    void shouldThrowExceptionWhenNoPaymentMethodIsSet(){}
}