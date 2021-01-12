package com.pizzaservice.pizza.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Entity
@Table(name = "pizzaorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Basic
    @ElementCollection
    @Column(name = "pizza_type_id")
    private List<Long> orderedItemIds;

    @Transient
    @NotEmpty(message = "Ordered items cannot be empty")
    private List<PizzaType> orderedItems = new ArrayList<>();

    //todo: check if we need basic here
    //@Basic
    private Long paymentMethodId;

    @Transient
    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;

    private String comment;

    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    @PostLoad
    void fillTransient(){
        if (paymentMethodId > 0){
            this.paymentMethod = PaymentMethod.getById(paymentMethodId);
        }

        for (Long id: orderedItemIds){
            orderedItems.add(PizzaType.getById(id));
        }
    }

    @PrePersist
    void fillPersistent(){
        if (paymentMethod != null){
            this.paymentMethodId = paymentMethod.getId();
        }

        orderedItemIds = new ArrayList<>();
        for (PizzaType type: orderedItems){
            orderedItemIds.add(type.getId());
        }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("Order #");
        builder.append(id);
        builder.append(" placed by ");
        //todo: placed by id?
        builder.append(userId);
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

    public Long getId() {
        return id;
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

    public void setOrderedItems(List<PizzaType> pizzaTypes){
        orderedItems = pizzaTypes;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderedItems, order.orderedItems) &&
                paymentMethod == order.paymentMethod &&
                Objects.equals(comment, order.comment) &&
                Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderedItems, paymentMethod, comment, userId);
    }
}
