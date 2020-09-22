package com.pizzaservice.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryImplTest {
    OrderRepositoryImpl repository = OrderRepositoryImpl.getInstance();

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //when
        repository.addOrder(order);

        //then
        assertThat(repository.getOrders(), Matchers.hasItem(order));
    }

    @Test
    void shouldAssignANumberToOrderTaken(){
        //TODO: not sure how to test that
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        repository.addOrder(order);

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
        repository.addOrder(order);
        repository.addOrder(anotherOrder);

        //when
        List<Order> orders = repository.getOrders();

        //then
        assertThat(orders, Matchers.hasItems(order, anotherOrder));
    }

    @Test
    @Disabled
    void shouldReturnOrderByOrderNumber(){
        //TODO
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        repository.addOrder(order);
        int orderNumber = order.getOrderNumber();

        //when
        Order actualOrder = repository.getOrder(orderNumber);

        //then
        assertEquals(order, actualOrder);
    }
}