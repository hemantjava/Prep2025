package com.prep.interview.bitwise;
/*
XOR all elements of both arrays.
Same numbers cancel each other (a ^ a = 0).
Remaining number = missing one.

Complexity:
Time: O(n)
Space: O(1)
 */
public class MissingNumberXOR {
    public static int findMissingByXOR(int[] arr1, int[] arr2) {
        int xor = 0;
        for (int num : arr1) xor ^= num;
        for (int num : arr2) xor ^= num;
        return xor;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 1, 5, 4};
        System.out.println("Missing number: " + findMissingByXOR(arr1, arr2));
    }

    //Other way
    //Sum all elements of both arrays → the difference = missing number.
    public static int findMissingBySum(int[] arr1, int[] arr2) {
        int sum1 = 0, sum2 = 0;

        for (int num : arr1) sum1 += num;
        for (int num : arr2) sum2 += num;

        return sum1 - sum2;  // Missing number
    }
}
