package com.pizzaservice.pizza.domain;

import java.math.BigDecimal;

public interface PaymentCalculator {
    BigDecimal calculateOrderPayment(Order order);
}
