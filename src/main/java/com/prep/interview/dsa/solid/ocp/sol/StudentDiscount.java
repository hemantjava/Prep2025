package com.prep.interview.dsa.solid.ocp.sol;

public class StudentDiscount implements Discount {
    @Override
    public double getDiscount(double discount) {
        return discount * 0.10;
    }
}
