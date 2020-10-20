package com.pizzaservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.pizzaservice.service.PaymentMethod.CASH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

class OrderSingletonImplTest {
    private OrderSingleton orderSingleton;

    @BeforeEach
    void setup(){
        orderSingleton = OrderSingleton.getInstance();
    }

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        //when
        orderSingleton.addOrder(order);

        //then
        assertThat(orderSingleton.getOrders(), hasItem(order));
    }

    @Test
    void shouldAssignANumberToOrderTaken(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        orderSingleton.addOrder(order);

        //when
        int orderNumber = order.getOrderNumber();

        //then
        int expected = orderSingleton.getOrders().size();
        assertEquals(expected, orderNumber);
    }

    @Test
    void shouldReturnOrders(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        Order anotherOrder = new Order();
        anotherOrder.addItem("calzone");
        anotherOrder.setPaymentMethod(CASH);

        orderSingleton.addOrder(order);
        orderSingleton.addOrder(anotherOrder);

        //when
        List<Order> orders = orderSingleton.getOrders();

        //then
        assertThat(orders, hasItems(order, anotherOrder));
    }
}