package com.pizzaservice.pizza.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class PaymentServiceImpl implements PaymentService{
    private static final Logger LOG = LogManager
            .getLogger(PaymentServiceImpl.class.getName());

    private PaymentStatusResolver resolver;

    @Autowired
    PaymentServiceImpl(PaymentStatusResolver resolver){
        this.resolver = resolver;
    }

    @Override
    public PaymentStatus processOrderPayment(Order order, BigDecimal customerPayment) {
//        if (resolver.getPaymentStatus(order, customerPayment).equals(PaymentStatus.SUCCESS)){
//            LOG.info("Order {} payment successful", order.getId());
//        } else {
//            LOG.info("Order {} payment failed with {} payed", order.getId(), customerPayment);
//        }
        return resolver.getPaymentStatus(order, customerPayment);
    }
}
