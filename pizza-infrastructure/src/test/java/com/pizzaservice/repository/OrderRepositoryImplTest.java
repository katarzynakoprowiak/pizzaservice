package com.pizzaservice.repository;

import com.pizzaservice.model.Order;
import com.pizzaservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pizzaservice.model.PaymentMethod.CASH;
import static com.pizzaservice.model.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

@ExtendWith(SpringExtension.class)
@ContextConfiguration (classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
class OrderRepositoryImplTest {
    @Autowired
    private OrderRepository orderRepository;
    private static final User USER = new User("Order", "User", 1.0);

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);
        order.setUser(USER);

        //when
        orderRepository.add(order);
        System.out.println("added order");

        //then
        assertThat(orderRepository.getAll(), hasItem(order));
    }

    @Test
    void shouldReturnOrders(){
        //given
        Order order = new Order();
        order.addItem("calzone");
        order.setPaymentMethod(CASH);
        order.setComment("comment");
        order.setUser(USER);

        Order anotherOrder = new Order();
        anotherOrder.addItems(Arrays.asList("margherita", "funghi"));
        anotherOrder.setPaymentMethod(CREDIT_CARD);
        anotherOrder.setUser(USER);

        orderRepository.add(order);
        orderRepository.add(anotherOrder);

        //when
        List<Order> orders = orderRepository.getAll();

        //then
        assertThat(orders, hasItems(order, anotherOrder));
    }
}