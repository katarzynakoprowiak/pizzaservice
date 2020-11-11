package com.pizzaservice.service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderService {
    //Optional<Set<ConstraintViolation<Order>>> takeOrder(Order order);
    void takeOrder(Order order);
    List<Order> getOrders();
}
