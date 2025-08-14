package com.prep.interview.array;

public class SortedArrayOrNot {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(isSorted(array));
    }

    //O(n)
    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }
}
