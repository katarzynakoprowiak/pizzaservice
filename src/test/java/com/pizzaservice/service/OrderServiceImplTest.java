package com.pizzaservice.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    OrderServiceImpl orderService;

    @Test
    void shouldTakeOrder(){
        //given
        orderService = new OrderServiceImpl(OrderRepositoryImpl.getInstance());
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //when
        orderService.takeOrder(order);

        //then
        assertThat(orderService.getOrders(), Matchers.hasItem(order));
    }

    @Test
    void shouldReturnOrdersFromRepository(){
        //given
        orderService = new OrderServiceImpl(OrderRepositoryImpl.getInstance());
        Order order = new Order.Builder()
                .addItem("funghi")
                .paymentMethod("cash")
                .build();
        Order anotherOrder = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("card")
                .build();

        //when
        orderService.takeOrder(order);
        orderService.takeOrder(anotherOrder);

        //then
        assertThat(orderService.getOrders(), Matchers.hasItems(order, anotherOrder));
    }

    @Test
    @Disabled
    void shouldReturnOrderBasedOnOrderNumber(){
        //TODO
        //given
        orderService = new OrderServiceImpl(OrderRepositoryImpl.getInstance());
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        orderService.takeOrder(order);
        int orderNumber = order.getOrderNumber();

        //when
        Order actualOrder = orderService.getOrderByNumber(orderNumber);

        //then
        assertEquals(order, actualOrder);
    }

    @Test
    @Disabled
    void shouldPrintOrders(){
        //TODO: another one I'm nor sure how to test
    }

    @Test
    @Disabled
    void shouldPrintInformationIfThereIsNoOrder(){
        //TODO: not sure how to test that
    }
}