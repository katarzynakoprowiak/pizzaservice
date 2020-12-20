package com.pizzaservice.model;

import org.apache.commons.text.WordUtils;

public enum PizzaType {
    MARGHERITA,
    CAPRICIOSA,
    FUNGHI,
    CALZONE;

    public static PizzaType getByString(String type){
        try{
            return valueOf(type.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(
                String.format("Pizza of type %s is unavailable.", type));
        }
    }

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(name());
    }
}
