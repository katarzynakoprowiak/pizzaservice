package com.pizzaservice.model;

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

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PizzaType.class, fetch = FetchType.EAGER)
    @NotEmpty(message = "Ordered items cannot be empty")
    private List<PizzaType> orderedItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;

    private String comment;

    @NotNull(message = "User cannot be null")
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("Order #");
        builder.append(id);
        builder.append(" placed by ");
        builder.append(user);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderedItems, paymentMethod, comment, user);
    }
}
