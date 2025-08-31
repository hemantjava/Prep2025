package com.prep.interview.bitwise;

public class Swapping {
    static void main() {
        int a = 5;
        int b = 7;
        System.out.println(a +":"+ b);
        a = a ^ b;  // a=2
        b = a ^ b;  //b=5
        a = a ^ b;  // a = 7
        System.out.println(a +":"+ b);

    }
}
