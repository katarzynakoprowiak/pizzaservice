package com.pizzaservice.pizza.domain;

import java.math.BigDecimal;

public interface PaymentStatusResolver {
    PaymentStatus getPaymentStatus(Order order, BigDecimal customerPayment);
}
