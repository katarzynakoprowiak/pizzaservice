package com.pizzaservice.pizza.domain;

/**
 * All PaymentMethod values are contained in payment_method table.
 */

public enum PaymentMethod {
    CASH(1L),
    CREDIT_CARD(2L);

    private final Long id;

    PaymentMethod(Long id){
        this.id = id;
    }

    public static PaymentMethod getById(Long id){
        for (PaymentMethod method : values()) {
            if(method.id.equals(id)){
                return method;
            }
        }
        throw new IllegalArgumentException(
                String.format("Payment method of id %d is unavailable.", id));
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString(){
        return name().replace("_", " ").toLowerCase();
    }
}
