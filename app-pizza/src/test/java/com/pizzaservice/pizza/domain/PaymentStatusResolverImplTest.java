package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.math.BigDecimal;

import static com.pizzaservice.pizza.domain.PaymentMethod.CASH;
import static com.pizzaservice.pizza.domain.PaymentMethod.CREDIT_CARD;
import static com.pizzaservice.pizza.domain.PaymentStatus.FAILURE;
import static com.pizzaservice.pizza.domain.PaymentStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentStatusResolverImplTest {
    private PaymentStatusResolver resolver;
    private PaymentCalculator calculator;

    @BeforeEach
    void setup(){
        calculator = mock(PaymentCalculator.class);
        resolver = new PaymentStatusResolverImpl(calculator);
    }

    @Test
    void shouldReturnSuccessIfOrderPaidByCard(){
        //given
        Order order = new Order();
        order.setPaymentMethod(CREDIT_CARD);

        when(calculator.calculateOrderPayment(order)).thenReturn(BigDecimal.ZERO);

        //when
        PaymentStatus cardPaymentStatus = resolver.getPaymentStatus(order, BigDecimal.ZERO);

        //then
        assertEquals(SUCCESS, cardPaymentStatus);
    }


    @Test
    void shouldReturnSuccessIfOrderFullyPaidByCash(){
        //given
        Order order = new Order();
        order.setPaymentMethod(CASH);
        BigDecimal orderValue = BigDecimal.valueOf(30);

        when(calculator.calculateOrderPayment(order)).thenReturn(orderValue);

        //when
        PaymentStatus paymentStatus = resolver.getPaymentStatus(order, orderValue);

        //then
        assertEquals(SUCCESS, paymentStatus);
    }


    @Test
    void shouldReturnFailureIfOrderNotFullyPaidByCash(){
        //given
        Order order = new Order();
        order.setPaymentMethod(CASH);
        BigDecimal orderValue = BigDecimal.valueOf(30);
        BigDecimal payment = orderValue.subtract(BigDecimal.ONE);

        when(calculator.calculateOrderPayment(order)).thenReturn(orderValue);

        //when
        PaymentStatus paymentStatus = resolver.getPaymentStatus(order, payment);

        //then
        assertEquals(FAILURE, paymentStatus);
    }

}