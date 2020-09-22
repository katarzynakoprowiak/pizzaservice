package com.pizzaservice.pizza;

public enum PizzaType {
    MARGHERITA("Margherita"),
    CAPRICIOSA("Capriciosa"),
    FUNGHI("Funghi"),
    CALZONE("Calzone");

    private String name;

    PizzaType(String name){
        this.name = name;
    }

    public static PizzaType getByString(String type){
        if ("margherita".equalsIgnoreCase(type)){
            return MARGHERITA;
        }
        if ("capriciosa".equalsIgnoreCase(type)){
            return CAPRICIOSA;
        }
        if ("funghi".equalsIgnoreCase(type)){
            return FUNGHI;
        }
        if ("calzone".equalsIgnoreCase(type)){
            return CALZONE;
        }
        
        throw new IllegalArgumentException(
                String.format("Pizza of type %s is unavailable.", type));
    }

    @Override
    public String toString() {
        return name;
    }
}
