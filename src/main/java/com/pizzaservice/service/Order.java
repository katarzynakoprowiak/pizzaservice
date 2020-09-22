package com.pizzaservice.service;

import com.pizzaservice.pizza.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {
    private int orderNumber;
    private final List<PizzaType> orderedItems;
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

        Stream<PizzaType> stream = orderedItems.stream();

        Map<String, Long> counted = stream
                .collect(Collectors.groupingBy(PizzaType::toString, Collectors.counting()));

        for (String pizzaType : counted.keySet()){
            builder.append(counted.get(pizzaType));
            builder.append(" ");
            builder.append(pizzaType);
            if (counted.get(pizzaType) > 1)
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

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<PizzaType> getOrderedItems() {
        return orderedItems;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public static class Builder {
        private final List<PizzaType> orderedItems;
        private PaymentMethod paymentMethod;
        private String comment;

        public Builder(){
            orderedItems = new ArrayList<>();
        }

        public Order build(){
            if (orderedItems.isEmpty())
                throw new IllegalStateException(
                        "There are no items on the order." +
                                " Please select items to order and try again.");
            if (paymentMethod == null)
                throw new IllegalStateException("No payment method was set.");

            return new Order(this);
        }

        public Builder addItem(String pizzaType){
            orderedItems.add(PizzaType.getByString(pizzaType));
            return this;
        }

        public Builder addItems(List<String> pizzaTypes){
            orderedItems.addAll(
                    pizzaTypes.stream().map(
                            pizzaType -> PizzaType.getByString(pizzaType))
                            .collect(Collectors.toList()));
            return this;
        }

        public Builder paymentMethod(String paymentMethod){
            this.paymentMethod = PaymentMethod.getByString(paymentMethod);
            return this;
        }

        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }
    }
}
