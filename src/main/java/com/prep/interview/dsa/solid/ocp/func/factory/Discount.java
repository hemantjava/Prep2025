package com.prep.interview.dsa.solid.ocp.func.factory;

public enum Discount {
    SEASONAL("seasonal"), LOYALTY("loyalty"), BULK("bulk");

    private final String discount;

    Discount(String discount) {
        this.discount = discount;
    }
    public String getDiscount() {
        return discount;
    }

}
