package com.pizzaservice.service;

import com.pizzaservice.pizza.PizzaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pizzaservice.service.PaymentMethod.CASH;
import static com.pizzaservice.service.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    public static final int ORDER_NUMBER = 1;
    OrderServiceImpl orderService;
    OrderRepository mockRepository;

    @BeforeEach
    void setup(){
        mockRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(mockRepository);
    }

    @Test
    void shouldTakeOrder() throws SQLException {
        //given
        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderNumber()).thenReturn(ORDER_NUMBER);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(PizzaType.CALZONE));
        when(mockOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockOrder.getComment()).thenReturn(null);

        when(mockRepository.getOrders()).thenReturn(Arrays.asList(mockOrder));

        //when
        orderService.takeOrder(mockOrder);

        //then
        assertThat(orderService.getOrders(), hasItem(mockOrder));
    }

    @Test
    void shouldReturnOrdersFromRepository() throws SQLException {
        //given
        Order mockOrder = mock(Order.class);
        when(mockOrder.getOrderNumber()).thenReturn(ORDER_NUMBER);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(PizzaType.FUNGHI));
        when(mockOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockOrder.getComment()).thenReturn(null);

        Order mockAnotherOrder = mock(Order.class);
        when(mockAnotherOrder.getOrderNumber()).thenReturn(ORDER_NUMBER);
        when(mockAnotherOrder.getOrderedItems()).thenReturn(Arrays.asList(PizzaType.MARGHERITA));
        when(mockAnotherOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockAnotherOrder.getComment()).thenReturn(null);

        when(mockRepository.getOrders()).thenReturn(Arrays.asList(mockOrder, mockAnotherOrder));

        //when
        orderService.takeOrder(mockOrder);
        orderService.takeOrder(mockAnotherOrder);

        //then
        assertThat(orderService.getOrders(), hasItems(mockOrder, mockAnotherOrder));
    }

    @Test
    void shouldReturnViolationIfEmptyOrderIsAdded(){
        Order order = new Order();

        Set<ConstraintViolation<Order>> orderViolations = orderService.getOrderViolations(order);

        List<String> messages = orderViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertThat(messages, hasItems("Ordered items cannot be empty", "Payment method cannot be null"));
    }
}