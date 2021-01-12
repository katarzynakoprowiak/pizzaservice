package com.pizzaservice.pizza.domain;

import com.pizzaservice.pizza.domain.Ingredient;
import com.pizzaservice.pizza.domain.IngredientType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    @Test
    void shouldPrintNoInformationAboutNumberOfPortionsIfSinglePortion(){
        //given
        Ingredient cheese = new Ingredient(IngredientType.CHEESE);

        //when
        String cheeseToString = cheese.toString();

        //then
        assertEquals("cheese", cheeseToString);
    }

    @Test
    void shouldPrintInformationAboutNumberOfPortionsIfMoreThanOnePortion(){
        //given
        Ingredient doubleCrust = new Ingredient(IngredientType.CRUST, 2);
        Ingredient tripleMushrooms = new Ingredient(IngredientType.MUSHROOMS, 3);
        Ingredient quadrupleHam = new Ingredient(IngredientType.HAM, 4);
        Ingredient loadOfCheese = new Ingredient(IngredientType.CHEESE, 5);

        //when
        String doubleCrustToString = doubleCrust.toString();
        String tripleMushroomsToString = tripleMushrooms.toString();
        String quadrupleHamToString = quadrupleHam.toString();
        String loadOfCheeseToString = loadOfCheese.toString();

        //then
        assertEquals("double crust", doubleCrustToString);
        assertEquals("triple mushrooms", tripleMushroomsToString);
        assertEquals("quadruple ham", quadrupleHamToString);
        assertEquals("5x cheese", loadOfCheeseToString);
    }

}