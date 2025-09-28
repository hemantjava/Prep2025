package com.prep.interview.dsa.solid.ocp.func;



public class Main {
    static void main(String[] args) {

        Customer studentDiscount = new Customer(DiscountStrategy.studentDiscountFunction);
        Customer regularDiscount = new Customer(DiscountStrategy.regularDiscountFunction);
        System.out.println(studentDiscount.getDiscount(1000d));
        System.out.println(regularDiscount.getDiscount(4000d));

        //--------------------------------------------


    }
}
