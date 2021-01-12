package com.pizzaservice.pizza.application;

import com.pizzaservice.pizza.domain.Ingredient;
import com.pizzaservice.pizza.domain.Pizza;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("pizzaPriceCalculator")
public class PizzaPriceCalculatorImpl implements PizzaPriceCalculator{

    @Override
    public BigDecimal calculatePrice(Pizza pizza, double priceModifier) {
        BigDecimal price = calculatePrice(pizza.getCrust());
        price = price.add(calculatePrice(pizza.getSauce()));
        price = price.add(pizza.getToppings().stream().map(this::calculatePrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        price = price.multiply(BigDecimal.valueOf(priceModifier));
        return price;
    }

    private BigDecimal calculatePrice(Ingredient ingredient){
        return ingredient.getType().getPrice().multiply(BigDecimal.valueOf(ingredient.getNumberOfPortions()));
    }
}
