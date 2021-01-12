package com.pizzaservice.pizza.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class PaymentCalculatorImpl implements PaymentCalculator{

    @Override
    public BigDecimal calculateOrderPayment(Order order) {
        BigDecimal orderValue = BigDecimal.valueOf(30);

        return orderValue;
    }

}
