package com.prep.interview.dsa.solid.ocp.prob;


public class Main {

    static void main() {
        Customer customer = new Customer();
        System.out.println(customer.getDiscount(11000));
        System.out.println(customer.getDiscount(6000));
    }
}
