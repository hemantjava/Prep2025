package com.prep.interview.core_java;

public class Swapping {
    static void main() {
        int a = 5;
        int b = 7;
        swap1(a, b);
        swap2(a, b);
        swap3(a, b);
        swap4(a, b);
    }
    private static void  swap1(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println(a +":" + b);
    }

    private static void swap2(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a +":" + b);
    }

    private static  void swap3(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a +":" + b);
    }

    private static void swap4(int a, int b) {
        a = a * b;
        b = a / b;
        a = a / b;
        System.out.println(a +":" + b);
    }
}

