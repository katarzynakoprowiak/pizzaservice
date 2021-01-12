package com.pizzaservice.pizza.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private static final Logger LOG = LogManager
            .getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final Validator validator;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, PaymentService service){
        this.orderRepository = orderRepository;
        this.paymentService = service;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    @Transactional
    public void takeOrder(Order order){
        Set<ConstraintViolation<Order>> violations = getOrderViolations(order);

        if (violations.isEmpty()){
            orderRepository.add(order);

            //todo - fix later
            paymentService.processOrderPayment(order, BigDecimal.ZERO);

            LOG.info("Added order #{}", order.getId());
        } else {
            LOG.warn(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
    }

    @Override
    @Transactional
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Set<ConstraintViolation<Order>> getOrderViolations(Order order) {
        return validator.validate(order);
    }
}
