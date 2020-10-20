package com.pizzaservice.service;

import com.pizzaservice.pizza.PizzaType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Order {
    private int orderNumber;

    @NotEmpty(message = "Ordered items cannot be empty")
    private final List<PizzaType> orderedItems = new ArrayList<>();

    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;
    private String comment;

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
                .collect(groupingBy(PizzaType::toString, counting()));

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

    public void addItem(String pizzaType){
        orderedItems.add(PizzaType.getByString(pizzaType));
    }

    public void addItems(List<String> pizzaTypes){
        orderedItems.addAll(
                pizzaTypes.stream().map(
                        pizzaType -> PizzaType.getByString(pizzaType))
                        .collect(toList()));
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}
