package com.pizzaservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.pizzaservice.pizza.PizzaType.*;
import static com.pizzaservice.service.PaymentMethod.CASH;
import static com.pizzaservice.service.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderRepositoryImplTest {
    private OrderRepositoryImpl orderRepository;

    @BeforeEach
    void setup(){
        orderRepository = new OrderRepositoryImpl();
    }

    @Test
    void shouldTakeOrder() throws SQLException {
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);

        //when
        order.setOrderNumber(orderRepository.addOrder(order));

        //then
        assertThat(orderRepository.getOrders(), hasItem(order));
    }

    @Test
    void shouldReturnOrderNumber() throws SQLException {
        //given
        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(CALZONE));
        when(mockOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockOrder.getComment()).thenReturn(null);

        //when
        int orderNumber = orderRepository.addOrder(mockOrder);

        //then
        int expected = orderRepository.getOrders().size();
        assertEquals(expected, orderNumber);
    }

    @Test
    void shouldReturnOrders() throws SQLException{
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);
        order.setComment("comment");

        Order anotherOrder = new Order();
        anotherOrder.addItems(Arrays.asList("margherita", "funghi"));
        anotherOrder.setPaymentMethod(CREDIT_CARD);

        order.setOrderNumber(orderRepository.addOrder(order));
        anotherOrder.setOrderNumber(orderRepository.addOrder(anotherOrder));

        //when
        List<Order> orders = orderRepository.getOrders();

        //then
        assertThat(orders, hasItems(order, anotherOrder));
    }
}