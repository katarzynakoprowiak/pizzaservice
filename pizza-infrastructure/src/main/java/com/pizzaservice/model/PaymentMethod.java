package com.pizzaservice.model;

public enum PaymentMethod {
    CASH,
    CREDIT_CARD;

    @Override
    public String toString(){
        return name().replace("_", " ").toLowerCase();
    }
}
