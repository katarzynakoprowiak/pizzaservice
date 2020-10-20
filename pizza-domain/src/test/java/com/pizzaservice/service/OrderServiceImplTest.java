package com.pizzaservice.service;

import org.junit.jupiter.api.Test;

import static com.pizzaservice.service.PaymentMethod.CASH;
import static com.pizzaservice.service.PaymentMethod.CREDIT_CARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

class OrderServiceImplTest {
    private static final OrderServiceImpl ORDER_SERVICE
            = new OrderServiceImpl(OrderSingleton.getInstance());

    @Test
    void shouldTakeOrder(){
        //given
        Order order = new Order();
                order.addItem("calzone");
                order.setPaymentMethod(CASH);

        //when
        ORDER_SERVICE.takeOrder(order);

        //then
        assertThat(ORDER_SERVICE.getOrders(), hasItem(order));
    }

    @Test
    void shouldReturnOrdersFromRepository(){
        //given
        Order order = new Order();
        order.addItem("funghi");
        order.setPaymentMethod(CASH);

        Order anotherOrder = new Order();
        anotherOrder.addItem("calzone");
        anotherOrder.setPaymentMethod(CREDIT_CARD);

        //when
        ORDER_SERVICE.takeOrder(order);
        ORDER_SERVICE.takeOrder(anotherOrder);

        //then
        assertThat(ORDER_SERVICE.getOrders(), hasItems(order, anotherOrder));
    }
}