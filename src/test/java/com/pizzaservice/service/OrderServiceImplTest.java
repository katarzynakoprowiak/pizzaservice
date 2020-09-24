package com.pizzaservice.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    private static final OrderServiceImpl ORDER_SERVICE
            = new OrderServiceImpl(OrderRepositoryImpl.getInstance());

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();

        //when
        ORDER_SERVICE.takeOrder(order);

        //then
        assertThat(ORDER_SERVICE.getOrders(), hasItem(order));
    }

    @Test
    void shouldReturnOrdersFromRepository(){
        //given
        Order order = new Order.Builder()
                .addItem("funghi")
                .paymentMethod("cash")
                .build();
        Order anotherOrder = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("card")
                .build();

        //when
        ORDER_SERVICE.takeOrder(order);
        ORDER_SERVICE.takeOrder(anotherOrder);

        //then
        assertThat(ORDER_SERVICE.getOrders(), hasItems(order, anotherOrder));
    }

    @Test
    void shouldReturnOrderBasedOnOrderNumber(){
        //given
        Order order = new Order.Builder()
                .addItem("calzone")
                .paymentMethod("cash")
                .build();
        ORDER_SERVICE.takeOrder(order);
        int orderNumber = order.getOrderNumber();

        //when
        Order actualOrder = ORDER_SERVICE.getOrderByNumber(orderNumber);

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