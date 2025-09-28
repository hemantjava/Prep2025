package com.prep.interview.dsa.solid.ocp.func;

import java.util.function.Function;

public record Customer(Function<Double, Double> discountStrategy) {

    // Inject discount strategy at runtime
    public double getDiscount(double amount) {
        return discountStrategy.apply(amount);
    }


}
