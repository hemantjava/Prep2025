package com.prep.interview.java8.functionalInterface;

public class Example {
    public static void main(String[] args) {
        MyInterface.newMethodStatic();
        MyInterface myInterface = (a, b) -> a + b;
        System.out.println(myInterface.sum(1, 3));
        myInterface.newMethod();
    }


}
