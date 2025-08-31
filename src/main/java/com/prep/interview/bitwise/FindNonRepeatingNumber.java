package com.prep.interview.bitwise;

public class FindNonRepeatingNumber {
    static void main() {
        int[] arr = new int[]{5,4,1,4,3,5,1};// other number repeats twice
        int rs = 0;
        for (int i : arr) {
            rs ^= i;
        }
        System.out.println(rs);//3 O(n)
    }
    /*
     Note: (5^5) = 0  and (0^3) = 3  . 0 XOR with any number we will get the same number
     */
}
