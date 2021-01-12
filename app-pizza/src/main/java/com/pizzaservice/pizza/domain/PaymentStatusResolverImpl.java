package com.pizzaservice.pizza.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.pizzaservice.pizza.domain.PaymentStatus.FAILURE;
import static com.pizzaservice.pizza.domain.PaymentStatus.SUCCESS;

@Component
class PaymentStatusResolverImpl implements PaymentStatusResolver{
    private final PaymentCalculator calculator;

    @Autowired
    PaymentStatusResolverImpl(PaymentCalculator calculator){
        this.calculator = calculator;
    }

    @Override
    public PaymentStatus getPaymentStatus(Order order, BigDecimal customerPayment) {
        BigDecimal orderPayment = calculator.calculateOrderPayment(order);
        if (customerPayment.compareTo(orderPayment) < 0 && order.getPaymentMethod().equals(PaymentMethod.CASH)){
            return FAILURE;
        }
        return SUCCESS;
    }
}
