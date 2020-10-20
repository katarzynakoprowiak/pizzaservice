package com.pizzaservice.util;

public class PizzaUtil {
    public static final String DEFAULT_COUNT = "0";
    
    public static String numberToMultiplicationDescription(int i){
        if (i == 1)
            return "%s";
        if (i == 2)
            return "double %s";
        if (i == 3)
            return "triple %s";
        if (i == 4)
            return "quadruple %s";
        return String.format("%dx ", i) + "%s";
    }
}
