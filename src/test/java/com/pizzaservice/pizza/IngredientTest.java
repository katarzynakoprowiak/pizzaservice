package com.pizzaservice.pizza;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    @Test
    void shouldPrintIngredientInLowerCaseWithNoUnderscore(){
        //TODO: perhaps there is a hamcrest matcher for that
        List<String> expected = Arrays.asList(
                "crust",
                "rolled crust",
                "tomato sauce",
                "cheese",
                "ham",
                "mushrooms",
                "artichokes",
                "bell pepper",
                "onion");
        assertEquals("crust", Ingredient.CRUST.toString());
    }

}