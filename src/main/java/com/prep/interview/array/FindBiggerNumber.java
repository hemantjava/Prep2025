package com.prep.interview.array;

public class FindBiggerNumber {
    static void main() {
        int[] array = {1, 4, 2, 3, 9, 5, 6};
        System.out.println(getBiggerNumber(array));//9
    }

    static int getBiggerNumber(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }
}
