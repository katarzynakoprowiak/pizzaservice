package com.pizzaservice.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
    private static final Logger LOG = LogManager
            .getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orderRepository;
    private final Validator validator;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public void takeOrder(Order order){
        Set<ConstraintViolation<Order>> violations = getOrderViolations(order);

        if (violations.isEmpty()){
            try{
                order.setOrderNumber(orderRepository.addOrder(order));
            } catch (SQLException exception){
                LOG.warn("Error writing order to the repository.", exception);
            }
            LOG.info("Added order #{}", order.getOrderNumber());
        } else {
            LOG.warn(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
    }

    public List<Order> getOrders(){
        List<Order> orders = null;
        try{
            orders = orderRepository.getOrders();
        } catch (SQLException exception) {
            LOG.warn("Error accessing repository order data.", exception);
        }
        return orders;
    }

    public Set<ConstraintViolation<Order>> getOrderViolations(Order order) {
        return validator.validate(order);
    }
}
