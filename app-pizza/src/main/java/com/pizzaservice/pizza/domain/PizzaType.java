package com.pizzaservice.pizza.domain;

import org.apache.commons.text.WordUtils;

/**
 * All PizzaType values are contained in pizza_type table.
 */

public enum PizzaType {
    MARGHERITA(1L),
    CAPRICIOSA(2L),
    FUNGHI(3L),
    CALZONE(4L);

    private final Long id;

    PizzaType(Long id){
        this.id = id;
    }

    public static PizzaType getById(Long id){
        for (PizzaType type : values()) {
            if(type.id.equals(id)){
                return type;
            }
        }
        throw new IllegalArgumentException(
                String.format("Pizza type of id %d is unavailable.", id));
    }

    public static PizzaType getByString(String type){
        try{
            return valueOf(type.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(
                String.format("Pizza of type %s is unavailable.", type));
        }
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return WordUtils.capitalizeFully(name());
    }
}
