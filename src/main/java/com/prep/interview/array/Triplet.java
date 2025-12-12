package com.prep.interview.array;

import java.util.*;
import java.util.stream.IntStream;

public class Triplet {
    static void main() {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int target = 0;
        System.out.println(hasTripletSum(arr, target));
        System.out.println(hasTripletSum1(arr, target));
        System.out.println(hasTripletSumExpected(arr, target));
        System.out.println(findTriplet(arr, target));
    }

    // Optimal using Hashing TC: O(n^2) and SC: O(n)
    static List<List<Integer>> hasTripletSum(int[] arr, int target) {
        List<List<Integer>> list = new ArrayList<>();
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
                    list.add(Arrays.asList(arr[i], arr[j], second));
                }

                // Add arr[j] as a potential second element
                st.add(arr[j]);
            }
        }

        return list;
    }

    // Brute Force TC: O(n^3) and SC: O(1)

    static List<List<Integer>> hasTripletSum1(int[] arr, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int n = arr.length;

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Fix the second element as arr[j]
            for (int j = i + 1; j < n - 1; j++) {

                // Now look for the third number
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target)
                        list.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    // return true; // If a triplet is found
                }
            }
        }

        return list;
    }


    //Expected optimal solution TC: O(n^2) and SC: O(1), without extra space using sorting and two pointers
    static List<List<Integer>> hasTripletSumExpected(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();

        // Fix the first element as arr[i]
        for (int i = 0; i < n - 2; i++) {

            // Initialize left and right pointers with
            // start and end of remaining subarray
            int l = i + 1, r = n - 1;

            while (l < r) {
                int currentSum = arr[i] + arr[l] + arr[r];
                if (currentSum == target) {
                    list.add(Arrays.asList(arr[i], arr[l], arr[r]));
                    l++;
                    r--;
                } else if (currentSum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return list;
    }

    private static List<List<Integer>> findTriplet(int[] arr, int target) {
        int n = arr.length;
        return IntStream.range(0, n - 2)
                .boxed()
                .flatMap(i ->
                        IntStream.range(i + 1, n - 1)
                                .boxed()
                                .flatMap(j ->
                                        IntStream.range(j + 1, n)
                                                .filter(k -> arr[i] + arr[j] + arr[k] == target)
                                                .mapToObj(l -> Arrays.asList(arr[i], arr[j], arr[l]))
                                )
                )
                .toList();
    }

}
