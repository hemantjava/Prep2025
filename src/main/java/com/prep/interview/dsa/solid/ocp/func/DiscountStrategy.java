package com.prep.interview.dsa.solid.ocp.func;

import java.util.function.Function;

public class DiscountStrategy {
    // Different discount strategies using lambdas
    public static Function<Double, Double> studentDiscountFunction = total -> total * 0.10;
    public static Function<Double, Double> regularDiscountFunction = total -> total * 0.05;
}
