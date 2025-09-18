package com.prep.interview.array;

import java.util.HashSet;
import java.util.Set;

public class Triplet {
    static void main() {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int target = 13;
        System.out.println(hasTripletSum(arr, target));
        System.out.println(hasTripletSum(arr, target));
    }

    // Using Set TC: O(n^2) and SC: O(n)
    static boolean hasTripletSum(int[] arr, int target) {
        int n = arr.length;

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Hash set to store potential second elements
            Set<Integer> st = new HashSet<>();

            // Fix the third element as arr[j]
            for (int j = i + 1; j < n; j++) {
                int second = target - arr[i] - arr[j];

                // Search for second element in hash set
                if (st.contains(second)) {
                    return true;
                }

                // Add arr[j] as a potential second element
                st.add(arr[j]);
            }
        }

        return false;
    }

    static boolean hasTripletSum1(int[] arr, int target) {
        int n = arr.length;

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Fix the second element as arr[j]
            for (int j = i + 1; j < n - 1; j++) {

                // Now look for the third number
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target)
                        return true; // If a triplet is found
                }
            }
        }

        return false;
    }

}
