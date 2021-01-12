package com.pizzaservice.pizza.domain;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentStatus processOrderPayment(Order order, BigDecimal customerPayment);
}
