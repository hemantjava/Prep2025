package com.prep.interview.company.photon;

public class PrintNumber {
    public static void main(String[] args) {
        print(10);
    }

    private static void print(int num) {
        if (num == 0)
            return;
        System.out.println(num+" ");//print before each recursion call
        print(num - 1);
        System.out.println(num+" ");//print after a base case met
    }
}
