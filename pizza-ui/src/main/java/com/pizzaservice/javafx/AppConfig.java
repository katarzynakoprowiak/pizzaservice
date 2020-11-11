package com.pizzaservice.javafx;

import com.pizzaservice.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.pizzaservice"})
public class AppConfig {
/*
    @Bean(name = "orderService")
    public OrderService getOrderService(){
        return new OrderServiceImpl(getOrderRepository());
    }

    @Bean(name = "orderRepository")
    public OrderRepository getOrderRepository(){
        return OrderSingleton.getInstance();
    }

    @Bean(name = "pizzaService")
    public PizzaService getPizzaService(){
        return new PizzaServiceImpl(getPizzaFactory());
    }

    @Bean(name = "pizzaFactory")
    public PizzaFactory getPizzaFactory(){
        return new PizzaFactoryImpl();
    }*/
}
