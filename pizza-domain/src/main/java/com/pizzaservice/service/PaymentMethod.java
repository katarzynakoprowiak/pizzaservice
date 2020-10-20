package com.pizzaservice.service;

public enum PaymentMethod {
    CASH,
    CREDIT_CARD;

    @Override
    public String toString(){
        return name().replace("_", " ").toLowerCase();
    }
}
