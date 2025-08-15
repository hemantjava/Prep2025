package com.prep.interview.dsa.searching;

public class FastAndSlow {
    //To finding middle elements
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};//
        System.out.println(fastAndSlow(arr));//5
        System.out.println(arr[fastAndSlow(arr)]);//6
    }

    private static int fastAndSlow(int[] arr) {
        int slow = 0;
        int fast = 0;
        while (fast < arr.length) {
            slow += 1;
            fast += 2;
        }
        return slow;//middle element
    }
}
