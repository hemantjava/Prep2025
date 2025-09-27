package com.prep.interview.dsa.solid.ocp.sol;

public class RegularDiscount implements Discount {
    @Override
    public double getDiscount(double discount) {
        return discount * 0.05;
    }
}
