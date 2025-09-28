package com.prep.interview.dsa.solid.ocp.func.factory;

public class Customer {
    public double getDiscount(Discount discount, double totSale) {
       return DiscountFactory.apply(discount, totSale);
    }

}