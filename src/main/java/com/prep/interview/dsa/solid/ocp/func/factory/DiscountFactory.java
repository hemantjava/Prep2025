package com.prep.interview.dsa.solid.ocp.func.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DiscountFactory {
    private static final Map<Discount, Function<Double, Double>> strategies = new ConcurrentHashMap<>();

    static {
        strategies.put(Discount.SEASONAL, amt -> amt * 0.10);
        strategies.put(Discount.LOYALTY, amt -> amt * 0.15);
        strategies.put(Discount.BULK, amt -> amt * 0.80);
    }

    public static double apply(Discount type, double amount) {
        return strategies.getOrDefault(type, v -> v).apply(amount);
    }
}

