package com.pizzaservice.pizza.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PizzaServiceImpl implements PizzaService{
    private static final Logger LOG = LogManager
            .getLogger(PizzaServiceImpl.class.getName());

    private final PizzaFactory factory;

    @Autowired
    public PizzaServiceImpl(PizzaFactory factory){
        this.factory = factory;
    }

    @Override
    public List<Pizza> makePizza(Order order) {
        LOG.info("Prepared order #{}", order.getId());
        List<Pizza> pizzas =  order.getOrderedItems().stream()
                .map(factory::makePizza)
                .collect(toList());
        return pizzas;
    }
}
