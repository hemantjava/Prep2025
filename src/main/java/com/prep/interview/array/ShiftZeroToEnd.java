package com.prep.interview.array;

import java.util.Arrays;

public class ShiftZeroToEnd {
    public static void main(String[] args) {
        int[] arr = {1, 0, 7, 8, 9, 0, 0, 1, 2}; //1st occurrence zero swapped with after zero 1st occurrence number
        //shiftZeroToEnd(arr);
        //shiftZeroToEnd1(arr);
        shiftZeroToStart(arr);
        System.out.println(Arrays.toString(arr));
    }

    //Note: writeIndex increment when non 0 occurred and i index occurred every iteration
    private static void shiftZeroToEnd(int[] arr) {
        int writeIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                if (arr[writeIndex] == 0) {//swapping
                    int temp = arr[i];
                    arr[i] = arr[writeIndex];
                    arr[writeIndex] = temp;
                }
                writeIndex++;
            }
        }
    }


    private static void shiftZeroToStart(int[] arr) {
        int writeIndex = arr.length - 1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                if (arr[writeIndex] == 0) {
                    int temp = arr[writeIndex];
                    arr[writeIndex] = arr[i];
                    arr[i] = temp;

                }
                writeIndex--;
            }
        }
    }
}
