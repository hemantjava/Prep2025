package com.prep.interview.array;

import java.util.Arrays;

public class FindThreeSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 9, 6, 8};
        int target = 23;
        Arrays.sort(arr); //[1,3,5,6,8,9]
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    System.out.println(arr[left] + "," + arr[right] + "," + arr[i]);
                    left++;
                    right--;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
    }
}
