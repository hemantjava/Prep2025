package com.prep.interview.dsa.solid.ocp.prob;

public class Customer {


    //OCP if we want to add new discount to the customer then need to modify this class method
    //that breaking OCP principle
    public double getDiscount(double totalSal) {
        if (totalSal >= 10000) {
            return totalSal * 0.10;
        } else if (totalSal >= 5000) {
            return totalSal * 0.05;
        }
        return totalSal * 0.0;
    }
}
