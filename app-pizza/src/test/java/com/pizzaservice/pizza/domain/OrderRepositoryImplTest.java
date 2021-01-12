package com.pizzaservice.pizza.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.pizzaservice.pizza.domain.PaymentMethod.CASH;
import static com.pizzaservice.pizza.domain.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

@ExtendWith(SpringExtension.class)
@ContextConfiguration (classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
class OrderRepositoryImplTest {
    @Autowired
    private OrderRepository orderRepository;
    private static final Long USER_ID = 1L;

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);
        order.setUserId(USER_ID);

        //when
        orderRepository.add(order);
        System.out.println("added order");

        //then
        assertThat(orderRepository.findAll(), hasItem(order));
    }

    @Test
    void shouldReturnOrders(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);
        order.setComment("comment");
        order.setUserId(USER_ID);

        Order anotherOrder = new Order();
        anotherOrder.addItems(Arrays.asList("margherita", "funghi"));
        anotherOrder.setPaymentMethod(CREDIT_CARD);
        anotherOrder.setUserId(USER_ID);

        orderRepository.add(order);
        orderRepository.add(anotherOrder);

        //when
        List<Order> orders = orderRepository.findAll();

        //then
        assertThat(orders, hasItems(order, anotherOrder));
    }
}