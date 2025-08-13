package com.prep.interview.array.reverse;

import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) {
        int[] ints = new int[]{1,2,4,6,7,8,9};
        reverse(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void reverse(int[] array) {
        int n = array.length;
        int[] temp = new int[n]; //using temporary array
        for (int i = 0; i < n; i++) {
            temp[i] = array[n - i - 1]; //reverse the array elements
        }
        for (int i = 0; i < n; i++) {
            array[i] = temp[i];
        }
    }

    public static void reverse1(int[] array) {
        if (array.length == 1 || array.length == 0)
            return;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
