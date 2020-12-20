package com.pizzaservice.service;

import com.pizzaservice.model.Order;
import com.pizzaservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pizzaservice.model.PaymentMethod.CASH;
import static com.pizzaservice.model.PizzaType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    public static final Long ORDER_NUMBER = 1L;
    OrderServiceImpl orderService;
    OrderRepository mockRepository;

    @BeforeEach
    void setup(){
        mockRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(mockRepository);
    }

    @Test
    void shouldTakeOrder(){
        //given
        Order mockOrder = mock(Order.class);
        when(mockOrder.getId()).thenReturn(ORDER_NUMBER);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(CALZONE));
        when(mockOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockOrder.getComment()).thenReturn(null);

        when(mockRepository.getAll()).thenReturn(Arrays.asList(mockOrder));

        //when
        orderService.takeOrder(mockOrder);

        //then
        assertThat(orderService.getOrders(), hasItem(mockOrder));
    }

    @Test
    void shouldReturnOrdersFromRepository(){
        //given
        Order mockOrder = mock(Order.class);
        when(mockOrder.getId()).thenReturn(ORDER_NUMBER);
        when(mockOrder.getOrderedItems()).thenReturn(Arrays.asList(FUNGHI));
        when(mockOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockOrder.getComment()).thenReturn(null);

        Order mockAnotherOrder = mock(Order.class);
        when(mockAnotherOrder.getId()).thenReturn(ORDER_NUMBER);
        when(mockAnotherOrder.getOrderedItems()).thenReturn(Arrays.asList(MARGHERITA));
        when(mockAnotherOrder.getPaymentMethod()).thenReturn(CASH);
        when(mockAnotherOrder.getComment()).thenReturn(null);

        when(mockRepository.getAll()).thenReturn(Arrays.asList(mockOrder, mockAnotherOrder));

        //when
        orderService.takeOrder(mockOrder);
        orderService.takeOrder(mockAnotherOrder);

        //then
        assertThat(orderService.getOrders(), hasItems(mockOrder, mockAnotherOrder));
    }

    @Test
    void shouldReturnViolationIfEmptyOrderIsAdded(){
        //given
        Order order = new Order();

        //when
        Set<ConstraintViolation<Order>> orderViolations = orderService.getOrderViolations(order);
        List<String> messages = orderViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        //then
        assertThat(messages, hasItems("Ordered items cannot be empty", "Payment method cannot be null", "User cannot be null"));
    }
}