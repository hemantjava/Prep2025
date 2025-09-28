package com.prep.interview.dsa.solid.ocp.func.factory;

public class Main {
    static void main() {
        Customer customer = new Customer();
        System.out.println(customer.getDiscount(Discount.BULK,1000));
        System.out.println(customer.getDiscount(Discount.SEASONAL,1000));
    }
}
