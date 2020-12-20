package com.pizzaservice.pricecalculator;

import com.pizzaservice.pizza.Ingredient;
import com.pizzaservice.pizza.Pizza;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.summingDouble;

@Component("pizzaPriceCalculator")
public class PizzaPriceCalculatorImpl implements PizzaPriceCalculator{

    @Override
    public double calculatePrice(Pizza pizza, double priceModifier) {
        double price = 0;
        price += calculatePrice(pizza.getCrust());
        price += calculatePrice(pizza.getSauce());
        price += pizza.getToppings().stream().map(this::calculatePrice).collect(summingDouble(Double::doubleValue));
        price *= priceModifier;
        return price;
    }

    private double calculatePrice(Ingredient ingredient){
        return ingredient.getType().getPrice() * ingredient.getNumberOfPortions();
    }
}
