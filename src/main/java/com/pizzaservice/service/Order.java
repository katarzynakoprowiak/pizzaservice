package com.pizzaservice.service;

import com.pizzaservice.pizza.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {
    public static class Builder {
        private final List<Pizza> orderedItems;
        private PaymentMethod paymentMethod;
        private String comment;
        private final PizzaFactory factory;

        public Builder(PizzaFactory factory){
            this.factory = factory;
            orderedItems = new ArrayList<>();
        }

        public Order build(){
            return new Order(this);
        }

        public Builder addItem(String pizzaType){
            orderedItems.add(factory.makePizza(pizzaType));
            return this;
        }

        public Builder paymentMethod(String paymentMethod){
            if (paymentMethod.equalsIgnoreCase("cash"))
                this.paymentMethod = PaymentMethod.cash;
            else if (paymentMethod.equalsIgnoreCase("card")
                    || paymentMethod.equalsIgnoreCase("credit card")){
                this.paymentMethod = PaymentMethod.creditCard;
            } else {
                throw new IllegalArgumentException("Payment option of choice not available." +
                        " Please select other payment type.");
            }
            return this;
        }

        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }
    }

    private int orderNumber;
    private final List<Pizza> orderedItems;
    private final PaymentMethod paymentMethod;
    private final String comment;

    private Order(Builder builder){
        this.orderedItems = builder.orderedItems;
        this.paymentMethod = builder.paymentMethod;
        this.comment = builder.comment;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("Order #");
        builder.append(orderNumber);
        builder.append(":\n");

        Stream<Pizza> stream = orderedItems.stream();

        Map<String, Long> counted = stream
                .collect(Collectors.groupingBy(Pizza::getName, Collectors.counting()));

        for (String pizzaName : counted.keySet()){
            builder.append(counted.get(pizzaName));
            builder.append(" ");
            builder.append(pizzaName);
            if (counted.get(pizzaName) > 1)
                builder.append("s");
            builder.append("\n");
        }

        builder.append("Payment method: ");
        builder.append(paymentMethod);
        builder.append("\n");

        if (comment != null && !comment.isEmpty()){
            builder.append("Comment: ");
            builder.append(comment);
        }

        return builder.toString();
    }

    public List<Pizza> getOrderedItems() {
        return orderedItems;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getComment() {
        return comment;
    }
}
