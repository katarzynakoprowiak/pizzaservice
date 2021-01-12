package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.pizzaservice.pizza.domain.PaymentStatus.FAILURE;
import static com.pizzaservice.pizza.domain.PaymentStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {
    private PaymentService service;
    private PaymentStatusResolver mockResolver;

    @BeforeEach
    void setup(){
        mockResolver = mock(PaymentStatusResolver.class);
        service = new PaymentServiceImpl(mockResolver);
    }

    @Test
    void shouldReturnPaymentStatus(){
        //given
        Order paidOrder = new Order();
        BigDecimal paidOrderPayment = BigDecimal.valueOf(50);

        Order unpaidOrder = new Order();
        BigDecimal unpaidOrderPayment = BigDecimal.ZERO;

        when(mockResolver.getPaymentStatus(paidOrder, paidOrderPayment)).thenReturn(SUCCESS);
        when(mockResolver.getPaymentStatus(unpaidOrder, unpaidOrderPayment)).thenReturn(FAILURE);

        //when
        PaymentStatus paidOrderStatus = service.processOrderPayment(paidOrder, paidOrderPayment);
        PaymentStatus unpaidOrderStatus = service.processOrderPayment(unpaidOrder, unpaidOrderPayment);

        //then
        assertEquals(SUCCESS, paidOrderStatus);
        assertEquals(FAILURE, unpaidOrderStatus);
    }

}