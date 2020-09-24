package com.pizzaservice.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryImplTest {
    private static final OrderRepositoryImpl REPOSITORY = OrderRepositoryImpl.getInstance();

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //when
        REPOSITORY.addOrder(order);

        //then
        assertThat(REPOSITORY.getOrders(), hasItem(order));
    }

    @Test
    @Disabled
    void shouldAssignANumberToOrderTaken(){
        //TODO: not sure how to test that
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        REPOSITORY.addOrder(order);

        //when
        int orderNumber = order.getOrderNumber();

        //then
        //assertThat(???, orderNumber);
    }

    @Test
    void shouldReturnOrders(){
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        Order anotherOrder = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        REPOSITORY.addOrder(order);
        REPOSITORY.addOrder(anotherOrder);

        //when
        List<Order> orders = REPOSITORY.getOrders();

        //then
        assertThat(orders, hasItems(order, anotherOrder));
    }

    @Test
    void shouldReturnOrderByOrderNumber(){
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        REPOSITORY.addOrder(order);
        int orderNumber = order.getOrderNumber();

        //when
        Order actualOrder = REPOSITORY.getOrder(orderNumber);

        //then
        assertEquals(order, actualOrder);
    }
}