package com.prep.interview.dsa.solid.ocp.func;

import java.util.function.Function;

public class Main {
    static void main() {

        Customer studentDiscount = new Customer(DiscountStrategy.studentDiscountFunction);
        Customer regularDiscount = new Customer(DiscountStrategy.regularDiscountFunction);
        System.out.println(studentDiscount.getDiscount(1000d));
        System.out.println(regularDiscount.getDiscount(4000d));
    }
}
