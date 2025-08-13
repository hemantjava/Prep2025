package com.prep.interview.java8.functionalInterface;

@FunctionalInterface
public interface MyInterface {
    default void newMethod() {
        System.out.println("New default method");
    }

    int sum(int num1, int num2);

    static void newMethodStatic() {
        System.out.println("New static method");
    }

    boolean equals(Object obj);
}
