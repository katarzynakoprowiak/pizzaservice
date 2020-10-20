package com.pizzaservice.pizza;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class IngredientTypeTest {
    @Test
    void shouldPrintIngredientInLowerCaseWithNoUnderscore(){
        //given & when
        List<String> actual = Stream.of(IngredientType.values())
                .map(i -> i.toString())
                .collect(Collectors.toList());

        //then
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
        assertThat(actual, equalTo(expected));
    }
}