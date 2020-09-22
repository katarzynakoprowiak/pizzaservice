package com.pizzaservice.service;

public enum PaymentMethod {
    CASH("cash"),
    CREDIT_CARD("credit card");

    private String name;

    PaymentMethod(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public static PaymentMethod getByString(String method){
        if ("cash".equalsIgnoreCase(method))
            return PaymentMethod.CASH;
        if ("card".equalsIgnoreCase(method)
                || "credit card".equalsIgnoreCase(method))
            return PaymentMethod.CREDIT_CARD;

        throw new IllegalArgumentException(
                String.format("Payment option of type %s is not available." +
                        " Please select other payment type.", method));
    }


}
