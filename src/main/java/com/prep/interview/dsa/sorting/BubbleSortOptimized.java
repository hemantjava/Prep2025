package com.prep.interview.dsa.sorting;


import java.util.Arrays;

public class BubbleSortOptimized {
    public static void main(String[] args) {
         int[] arr = {2, 5, 3, 8, 4, 2};
        //int[] arr = {1, 2, 3, 6, 6, 7};

        System.out.println("Before sorting:");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("After sorting:");
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 1)
            return;
        int n = arr.length;
        boolean swapped;
        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Inner loop for comparisons
            for (int j = 0; j < n - i - 1; j++) { //{2, 5, 3, 8, 4, 2};
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swaps, array is already sorted
            if (!swapped) break;
        }
    }
}
