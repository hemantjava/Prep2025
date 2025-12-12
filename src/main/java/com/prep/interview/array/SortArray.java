package com.prep.interview.array;

import java.util.Arrays;

public class SortArray {
    static void main() {
        int[] arr = {0, 1, 0, 2, 1, 0, 2, 1};
        sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sortArray(int[] arr) {
        int low = 0, high = arr.length - 1, mid = (low + high) / 2;
        while (low < mid) {
            if (arr[low] > arr[mid]) {
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
            } else {
                mid--;
            }
        }
        while (mid < high) {
            if (arr[mid] > arr[high]) {
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                mid++;
            } else {
                high--;
            }
        }
    }
}
