package com.prep.interview.dsa.solid.ocp.sol;

import com.prep.interview.dsa.solid.ocp.prob.Customer;

public class Main {

    public static void main(String[] args) {
        StudentDiscount studentDiscount = new StudentDiscount();
        RegularDiscount regularDiscount = new RegularDiscount();
        CustomerDiscount customer = new CustomerDiscount(regularDiscount);
        System.out.println(customer.getDiscount(10000));

    }
}
