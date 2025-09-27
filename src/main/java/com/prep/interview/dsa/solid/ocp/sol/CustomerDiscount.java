package com.prep.interview.dsa.solid.ocp.sol;

public class CustomerDiscount {

    final private Discount discount;
    public CustomerDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getDiscount(double totSale) {
        return discount.getDiscount(totSale);
    }
}
